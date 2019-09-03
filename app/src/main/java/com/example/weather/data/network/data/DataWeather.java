package com.example.weather.data.network.data;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataWeather {

    @SerializedName("coord")
    private PostCoord coord;

    @SerializedName("main")
    private PostMain main;

    private String name;
}
