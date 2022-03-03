package com.nab.ms.test.application.repository.weatherRepository.exception;

import com.nab.ms.test.application.api.ApiError;
import com.netflix.hystrix.exception.ExceptionNotWrappedByHystrix;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DownstreamException  extends RuntimeException implements ExceptionNotWrappedByHystrix {

    private String message;
    private ApiError apiError;

    private static final long serialVersionUID = -5048839389296938938L;
}
