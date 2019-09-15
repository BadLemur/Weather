package com.example.weather.data.network.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostWeather {

    private int id;

    private String main;

    private String description;

    private String icon;
}
