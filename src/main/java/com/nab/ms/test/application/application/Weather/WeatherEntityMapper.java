package com.nab.ms.test.application.application.Weather;

import com.nab.ms.test.application.repository.database.entity.Weather;
import org.springframework.stereotype.Component;

@Component
public class WeatherEntityMapper {

    public Weather mapWeatherResponse(com.nab.ms.test.application.repository.weatherRepository.dto.Weather weather, String city, String country, String apiKey) {
        return Weather.builder().country(weather.getId())
                .description(weather.getDescription())
                .city(city)
                .country(country)
                .apiKey(apiKey)
                .build();
    }
}
