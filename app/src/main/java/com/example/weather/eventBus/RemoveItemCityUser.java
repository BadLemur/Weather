package com.example.weather.eventBus;

import lombok.Getter;

@Getter
public class RemoveItemCityUser {
    private final long idWeather;

    public RemoveItemCityUser(long idWeather) {
        this.idWeather = idWeather;
    }
}
