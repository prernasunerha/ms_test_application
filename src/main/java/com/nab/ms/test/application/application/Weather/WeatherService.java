package com.nab.ms.test.application.application.Weather;

import com.nab.ms.test.application.api.WeatherResponse;
import com.nab.ms.test.application.repository.database.WeatherRepositoryWrapper;
import com.nab.ms.test.application.repository.database.entity.Weather;
import com.nab.ms.test.application.repository.weatherRepository.WeatherRepository;
import com.nab.ms.test.application.repository.weatherRepository.dto.WeatherDtoResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherService {

    private final WeatherRepository weatherRepository;

    private final WeatherEntityMapper weatherEntityMapper;

    private final WeatherRepositoryWrapper weatherRepositoryWrapper;

    private final WeatherResponseMapper weatherResponseMapper;

    public WeatherResponse getWeatherReport(@NotNull String city, String country, @NotNull String apiKey) {

        Optional<Weather> weather = weatherRepositoryWrapper.findByCityAndCountry(city , country);

        if(weather.isPresent()) {

            Weather entity = weather.get();
            log.info("Search data found in Database - {} ",entity.toString());

            // Wrapper to only extract and store Description field
            return WeatherResponse.builder().description(Collections.singletonList(entity.getDescription())).build();
        }

        WeatherDtoResponse weatherDtoResponse =  weatherRepository.getWeatherReport(city, country, apiKey);
        weatherDtoResponse.getWeatherList().stream()
                .map(weatherList -> weatherRepositoryWrapper.saveWeatherData(weatherEntityMapper.mapWeatherResponse(weatherList, city, country, apiKey)));
        return weatherResponseMapper.mapResponse(weatherDtoResponse);
    }
}
