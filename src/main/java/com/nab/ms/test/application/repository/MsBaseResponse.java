package com.nab.ms.test.application.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nab.ms.test.application.api.ApiErrorDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MsBaseResponse {
    private String errorId;
    private String message;
    private String informationLink;
    private List<ApiErrorDetails> details;

    @JsonIgnore
    private HttpStatus status;

    public void setStatus(HttpStatus httpStatus){
        this.status = httpStatus;
    }
}
