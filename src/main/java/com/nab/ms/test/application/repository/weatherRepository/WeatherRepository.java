package com.nab.ms.test.application.repository.weatherRepository;

import com.nab.ms.test.application.repository.weatherRepository.dto.WeatherDtoResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.StringJoiner;

@Repository
@RequiredArgsConstructor
@Slf4j
public class WeatherRepository {

    private final WebClient webClient;

    @Value("${infrastructure.services.weatherapi.endpoint}")
    private String weatherEndpoint;

    @Value("${infrastructure.services.weatherapi.path}")
    private String weatherPath;

    @Value("${infrastructure.services.weatherapi.apiKey}")
    private String weatherApiKey;

    private final static String QUERY_PARAM_CITY_NAME = "q";
    private final static String QUERY_PARAM_API_KEY = "appid";
    private final DownstreamErrorMapper downstreamErrorMapper;

    //private String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=05ff608e03afa55e7655de03be8dbb21";

    @HystrixCommand(commandKey = "getWeatherReport")
    public WeatherDtoResponse getWeatherReport(@NotNull String city, String country, @NotNull String apikey) {

        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(city).add(country);
        queryMap.add(QUERY_PARAM_CITY_NAME, joiner.toString());
        queryMap.add(QUERY_PARAM_API_KEY, apikey);

        try {
            return webClient.get()
                    .uri(weatherEndpoint, uriBuilder ->
                            uriBuilder.path(weatherPath)
                                    .queryParams(queryMap)
                                    .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(httpStatus -> httpStatus != HttpStatus.OK, downstreamErrorMapper::mapError)
                    .bodyToMono(WeatherDtoResponse.class).block();
        } finally {
            log.info("Open Weather Map API invoked");
        }
    }
}
