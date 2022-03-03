package com.nab.ms.test.application.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String errorId;
    private String message;
    private HttpStatus Status;
    private String informationLink;
    private List<ApiErrorDetails> details;

    public ApiError(HttpStatus status) {
        this.errorId = String.valueOf(status.value());
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this(status);
        this.message = ex.getMessage();
    }
}
