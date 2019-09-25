package com.example.weather.app.adapter.recyclerViewListCity;

import lombok.Getter;

@Getter
public class AdapterListCityItem {
    private long idWeather;
    private String nameCity;

    public AdapterListCityItem(long idWeather, String nameCity) {
        this.idWeather = idWeather;
        this.nameCity = nameCity;
    }
}