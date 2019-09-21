package com.example.weather.data.network.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostMain {
    private float temp;
    /*давление*/
    private int pressure;
    /*влажность*/
    private int humidity;
}
