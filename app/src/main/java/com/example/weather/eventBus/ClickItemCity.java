package com.example.weather.eventBus;

import lombok.Getter;

@Getter
public class ClickItemCity {
    private final long idWeather;
    private final String nameCity;

    public ClickItemCity(long idWeather, String nameCity) {
        this.idWeather = idWeather;
        this.nameCity = nameCity;
    }
}