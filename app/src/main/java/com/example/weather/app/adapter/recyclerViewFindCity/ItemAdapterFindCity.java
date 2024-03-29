package com.example.weather.app.adapter.recyclerViewFindCity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemAdapterFindCity {

    private long idWeather;

    private String nameCityEN;

    private String country;
}
