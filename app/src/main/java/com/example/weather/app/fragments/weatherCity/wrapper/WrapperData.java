package com.example.weather.app.fragments.weatherCity.wrapper;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WrapperData {

    private String nameCity;
    private String typeWeather;
    private String typeWeatherIcon;

    private String speedWind;
    private float degWind;

    private String temp;
    /*влажность*/
    private String humidity;
    /*давление*/
    private String pressure;

}