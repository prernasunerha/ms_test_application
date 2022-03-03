package unit.com.nab.ms.test.application.repository;

import com.nab.ms.test.application.repository.database.WeatherJpaRepository;
import com.nab.ms.test.application.repository.database.WeatherRepositoryWrapper;
import com.nab.ms.test.application.repository.database.entity.Weather;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
public class WeatherRepositoryWrapperTest {

    @InjectMocks
    private WeatherRepositoryWrapper weatherRepositoryWrapper;

    @Mock
    private WeatherJpaRepository weatherJpaRepository;

    @Test
   public void saveWeather_newRecord_savedSuccessfully() {
        Weather weatherEntity = Weather.builder().apiKey("testKey")
                .city("Melbourne")
                .country("AU")
                .description("sunny")
                .build();

        when(weatherJpaRepository.save(any())).thenReturn(weatherEntity);
        Weather dbResponse = weatherRepositoryWrapper.saveWeatherData(weatherEntity);

        assertNotNull(dbResponse);
    }


}
