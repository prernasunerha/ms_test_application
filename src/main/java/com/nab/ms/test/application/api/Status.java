package com.nab.ms.test.application.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Status {

    BAD_REQUEST("BAD_REQUEST", "Bad Request", "Bad Request", HttpStatus.BAD_REQUEST);

    private String errorId;
    private String errorMessage;
    private String issue;
    private HttpStatus httpStatus;
}
