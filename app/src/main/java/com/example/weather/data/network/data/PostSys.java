package com.example.weather.data.network.data;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSys {

    @SerializedName("sunrise")
    private long sunrise;

    @SerializedName("sunset")
    private long sunset;
}
