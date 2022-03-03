package unit.com.nab.ms.test.application.controller;

import com.nab.ms.test.application.api.WeatherResponse;
import com.nab.ms.test.application.application.Weather.WeatherService;
import com.nab.ms.test.application.controller.WeatherController;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @Mock
    private HttpServletRequest request;


    @Test
    public void getWeather_whenValidRequest_thenReturnSuccess() {

        WeatherResponse response = WeatherResponse.builder().description(Collections.singletonList("Overcast clouds")).build();

        when(weatherService.getWeatherReport("Melbourne", "AU", "c8aadb8f4504f95b5a9144313cd96f84")).thenReturn(response);

       ResponseEntity<WeatherResponse> responseEntity =
               weatherController.getWeatherReport("Melbourne", "AU", "c8aadb8f4504f95b5a9144313cd96f84", request);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

    }

}
