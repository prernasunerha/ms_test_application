package com.nab.ms.test.application.repository.weatherRepository.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDtoResponse {

    @JsonProperty("weather")
    private List<Weather> weatherList;

}
