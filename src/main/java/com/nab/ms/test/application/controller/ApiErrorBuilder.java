package com.nab.ms.test.application.controller;

import com.nab.ms.test.application.api.ApiError;
import com.nab.ms.test.application.api.ApiErrorDetails;
import com.nab.ms.test.application.api.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ApiErrorBuilder {
    public static ApiError buildErrorResponse(Exception exception, HttpStatus status) {
        return ApiError.builder()
                .errorId(status.toString())
                .message(exception.getMessage())
                .build();
    }

    private static List<ApiErrorDetails> buildDetails(Status status) {
        return Collections.singletonList(ApiErrorDetails.builder()
                .issue(status.getIssue())
                .build());
    }
}
