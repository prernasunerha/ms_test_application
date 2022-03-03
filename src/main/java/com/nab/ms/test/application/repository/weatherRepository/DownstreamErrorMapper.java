package com.nab.ms.test.application.repository.weatherRepository;

import com.nab.ms.test.application.api.ApiError;
import com.nab.ms.test.application.repository.weatherRepository.dto.DownstreamResponse;
import com.nab.ms.test.application.repository.weatherRepository.exception.DownstreamException;
import com.nab.ms.test.application.repository.weatherRepository.exception.DownstreamResponseValidationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import javax.xml.ws.WebServiceException;

@Component
public class DownstreamErrorMapper {

    public Mono<Throwable> mapError(ClientResponse clientResponse) {
        HttpStatus downStreamstatus = clientResponse.statusCode();

        return clientResponse.bodyToMono(DownstreamResponse.class)
                .doOnNext(downstreamResponse -> downstreamResponse.setStatus(downStreamstatus))
                .map(downstreamResponse -> downStreamstatus.is4xxClientError()
                && downStreamstatus != HttpStatus.TOO_MANY_REQUESTS
                ? mapDownstreamException(downstreamResponse)
                : new ServiceUnavailableException("Error response from downstream service"));
    }

    public Throwable mapValidationError(ConstraintViolationException constraintViolationException) {
        return new DownstreamResponseValidationException("Error while validating response", constraintViolationException);
    }

    public Mono<Throwable> mapUnknownStatus(ClientResponse clientResponse) {
        return Mono.error(new WebServiceException("Error from downstream"));
    }

    private DownstreamException mapDownstreamException(DownstreamResponse downstreamResponse) {
        ApiError apiError = ApiError.builder()
                .Status(downstreamResponse.getStatus())
                .message(downstreamResponse.getMessage())
                .build();

        return new DownstreamException("Exception from Open Weather with Status " + downstreamResponse.getStatus().toString(), apiError);
    }
}
