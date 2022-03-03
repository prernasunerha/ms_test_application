package unit.com.nab.ms.test.application.service;

import com.nab.ms.test.application.api.WeatherResponse;
import com.nab.ms.test.application.application.Weather.WeatherEntityMapper;
import com.nab.ms.test.application.application.Weather.WeatherResponseMapper;
import com.nab.ms.test.application.application.Weather.WeatherService;
import com.nab.ms.test.application.repository.database.WeatherRepositoryWrapper;
import com.nab.ms.test.application.repository.database.entity.Weather;
import com.nab.ms.test.application.repository.weatherRepository.WeatherRepository;
import com.nab.ms.test.application.repository.weatherRepository.dto.WeatherDtoResponse;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherEntityMapper weatherEntityMapper;

    @Mock
    private WeatherRepositoryWrapper weatherRepositoryWrapper;

    @Mock
    private WeatherResponseMapper weatherResponseMapper;

    @Test
    public void validRequest_inDB_successResponse() {

        Weather weatherEntity = Weather.builder().country("AU").city("Melbourne").apiKey("testKey").description("rainy").build();
        when(weatherRepositoryWrapper.findByCityAndCountry(any(), any())).thenReturn(Optional.ofNullable(weatherEntity));
        WeatherResponse response = weatherService.getWeatherReport("Melbourne", "AU", "testKey");

        assertThat(response.getDescription().get(0), is("rainy"));
        verify(weatherRepositoryWrapper, times(1)).findByCityAndCountry("Melbourne", "AU");
        verifyNoInteractions(weatherRepository);
    }

    @Test
    public void validRequest_notInDb_fetchFromWeatherApi() {
        WeatherDtoResponse response = WeatherDtoResponse.builder().weatherList(Collections.singletonList(
                com.nab.ms.test.application.repository.weatherRepository.dto.Weather.builder()
                        .description("sunny").icon("testIcon").id("123").main("testMain").build()
        )).build();
        when(weatherRepositoryWrapper.findByCityAndCountry(any(), any())).thenReturn(Optional.empty());
        when(weatherRepository.getWeatherReport(any(), any(), any())).thenReturn(response);
        when(weatherResponseMapper.mapResponse(any())).thenReturn(WeatherResponse.builder().description(Collections.singletonList("sunny")).build());
        WeatherResponse weatherResponse = weatherService.getWeatherReport("Melbourne", "AU", "testKey");

        assertThat(weatherResponse.getDescription().get(0), is("sunny"));
        verify(weatherRepository, times(1)).getWeatherReport("Melbourne", "AU", "testKey");
        verify(weatherRepositoryWrapper, times(1)).findByCityAndCountry("Melbourne", "AU");
    }

}
