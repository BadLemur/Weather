package com.example.weather.eventBus;

import lombok.Getter;

@Getter
public class ClickItemRecyclerView {
    private final long idWeather;

    public ClickItemRecyclerView(long idWeather) {
        this.idWeather = idWeather;
    }
}