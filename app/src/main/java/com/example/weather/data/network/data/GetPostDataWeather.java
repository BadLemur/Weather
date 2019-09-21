package com.example.weather.data.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPostDataWeather {

    @SerializedName("coord")
    private PostCoord coord;

    @SerializedName("main")
    private PostMain main;

    @SerializedName("weather")
    private List<PostWeather> weather;

    private String name;

    @SerializedName("sys")
    private PostSys sys;

    @SerializedName("wind")
    private PostWind wind;
}
