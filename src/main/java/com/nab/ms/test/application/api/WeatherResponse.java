package com.nab.ms.test.application.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private List<String> description;
}
