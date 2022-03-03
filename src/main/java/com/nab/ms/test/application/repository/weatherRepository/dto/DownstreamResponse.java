package com.nab.ms.test.application.repository.weatherRepository.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nab.ms.test.application.api.ApiErrorDetails;
import com.nab.ms.test.application.repository.MsBaseResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class DownstreamResponse extends MsBaseResponse {

    @Builder
    public DownstreamResponse(String errorId, String message, String informationLink,
                              List<ApiErrorDetails> details, HttpStatus status) {
        super(errorId, message, informationLink, details, status);
    }

}
