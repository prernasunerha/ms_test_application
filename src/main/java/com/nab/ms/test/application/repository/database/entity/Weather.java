package com.nab.ms.test.application.repository.database.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Table(name="weather")
public class Weather {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "description")
    private String description;
}
