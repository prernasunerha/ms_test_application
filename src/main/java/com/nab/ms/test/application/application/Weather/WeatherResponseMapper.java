package com.nab.ms.test.application.application.Weather;

import com.nab.ms.test.application.api.WeatherResponse;
import com.nab.ms.test.application.repository.weatherRepository.dto.Weather;
import com.nab.ms.test.application.repository.weatherRepository.dto.WeatherDtoResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class WeatherResponseMapper {

    public WeatherResponse mapResponse(WeatherDtoResponse weather) {
       return WeatherResponse.builder().description(
               weather.getWeatherList().stream().map(Weather::getDescription).collect(Collectors.toList())
       ).build();
    }
}
