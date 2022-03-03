package com.nab.ms.test.application.repository.database;

import com.nab.ms.test.application.repository.database.entity.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WeatherRepositoryWrapper {

    private final WeatherJpaRepository weatherJpaRepository;

    public Weather saveWeatherData(Weather weather) {
        return weatherJpaRepository.save(weather);
    }

    public Optional<Weather> getById(Long id) {
        return weatherJpaRepository.findById(id);
    }

    public Optional<Weather> findByCityAndCountry(String city, String country) {
        return weatherJpaRepository.findByCityAndCountry(city, country);
    }
}
