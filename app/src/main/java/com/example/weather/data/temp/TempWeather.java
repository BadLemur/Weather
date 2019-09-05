package com.example.weather.data.temp;

import com.example.weather.data.temp.data.TempLangs;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempWeather {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String nameCity;

    @SerializedName("country")
    private String country;

    @SerializedName("langs")
    private List<TempLangs> langs;
}