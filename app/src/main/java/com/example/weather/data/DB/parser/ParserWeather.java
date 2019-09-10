package com.example.weather.data.DB.parser;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParserWeather {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String nameCity;

    @SerializedName("country")
    private String country;

    @SerializedName("langs")
    private List<Map<String, String>> langs;
}