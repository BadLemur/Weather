package com.example.weather.app.fragments.weatherCity.presenter;

import com.example.weather.data.network.data.GetPostDataWeather;

public interface iPresenterWeatherCity {

    void setDataWeather(GetPostDataWeather getPostDataWeather);

    void setMotionSize(int height, int y1, int y2);
}
