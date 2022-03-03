package com.nab.ms.test.application.repository.weatherRepository.exception;

import com.netflix.hystrix.exception.ExceptionNotWrappedByHystrix;

public class DownstreamResponseValidationException extends RuntimeException implements ExceptionNotWrappedByHystrix {

    private static final long serialVersionUID = -504883938929938938L;

    public DownstreamResponseValidationException(String msg) {
        super(msg);
    }

    public DownstreamResponseValidationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
