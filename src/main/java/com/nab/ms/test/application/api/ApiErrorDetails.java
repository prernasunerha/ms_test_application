package com.nab.ms.test.application.api;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDetails {
    private String field;
    private String value;
    private String location;
    private String issue;
}
