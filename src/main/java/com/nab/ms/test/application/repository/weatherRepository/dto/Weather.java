package com.nab.ms.test.application.repository.weatherRepository.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    private String id;
    private String main;
    private String description;
    private String icon;
}
