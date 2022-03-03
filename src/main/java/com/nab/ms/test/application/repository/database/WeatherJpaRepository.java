package com.nab.ms.test.application.repository.database;


import com.nab.ms.test.application.repository.database.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherJpaRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findByCityAndCountry(String city, String country);
}
