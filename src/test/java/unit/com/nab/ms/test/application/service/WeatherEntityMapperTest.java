package unit.com.nab.ms.test.application.service;

import com.nab.ms.test.application.application.Weather.WeatherEntityMapper;
import com.nab.ms.test.application.repository.database.entity.Weather;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
public class WeatherEntityMapperTest {

    @InjectMocks
    private WeatherEntityMapper weatherEntityMapper;

    @Test
    public void validRequest_transformEntity() {
        com.nab.ms.test.application.repository.weatherRepository.dto.Weather response = com.nab.ms.test.application.repository.weatherRepository.dto.Weather
                .builder().description("sunny").icon("testIcon").id("123").main("testMain").build();

        Weather weatherEntity = weatherEntityMapper.mapWeatherResponse(response, "Melbourne", "AU", "testKey");

        assertThat(weatherEntity.getDescription(), is("sunny"));
        assertThat(weatherEntity.getApiKey(), is("testKey"));
        assertThat(weatherEntity.getCity(), is("Melbourne"));
        assertThat(weatherEntity.getCountry(), is("AU"));
    }
}
