package com.nab.ms.test.application.controller;

import com.nab.ms.test.application.api.WeatherResponse;
import com.nab.ms.test.application.application.Weather.WeatherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Validated
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@EnableCaching
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;

    @GetMapping(value = {"/openweather/weather"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherResponse> getWeatherReport(
            @RequestParam final @Valid String city,
            @RequestParam(required = false) final String country,
            @RequestParam final String apiKey,
            final HttpServletRequest request) {
        WeatherResponse response = weatherService.getWeatherReport(city, country, apiKey);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
