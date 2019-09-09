package com.example.weather.app.adapter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemAdapterFindCity {

    private long idWeather;

    private String nameCity;

    private String regionCity;

    private String country;
}
