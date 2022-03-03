package com.nab.ms.test.application.controller;

import com.nab.ms.test.application.api.ApiError;
import com.nab.ms.test.application.api.Status;
import com.nab.ms.test.application.repository.weatherRepository.exception.DownstreamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ValidationException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ApiError> handleValidationException(Exception exception, HttpServletRequest httpServletRequest) {
        log.error(httpServletRequest.toString());
        log.error("Error Occured exception=", exception);
        return new ResponseEntity<>(ApiErrorBuilder.buildErrorResponse(exception, HttpStatus.BAD_REQUEST), Status.BAD_REQUEST.getHttpStatus());
    }

    @ExceptionHandler({DownstreamException.class})
    public ResponseEntity<ApiError> handleDownStreamException(DownstreamException exception, HttpServletRequest httpServletRequest) {
        log.error(httpServletRequest.toString());
        log.error("Error Occured exception=", exception);
        return new ResponseEntity<>(ApiErrorBuilder.buildErrorResponse(exception, exception.getApiError().getStatus()), exception.getApiError().getStatus());
    }
}
