package com.example.weather.eventBus;

import lombok.Getter;

@Getter
public class ClickItemRecyclerView {
    private final long idWeather;
    private final String nameCity;

    public ClickItemRecyclerView(long idWeather, String nameCity) {
        this.idWeather = idWeather;
        this.nameCity = nameCity;
    }
}