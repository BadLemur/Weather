package com.example.weather.app.fragments.weatherCity.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.fragments.weatherCity.model.ModelWeatherCity;
import com.example.weather.app.fragments.weatherCity.model.iModelWeatherCity;
import com.example.weather.app.fragments.weatherCity.view.ViewWeatherCity;
import com.example.weather.data.network.data.DataWeather;

@InjectViewState
public class PresenterWeatherCity extends MvpPresenter<ViewWeatherCity> implements iPresenterWeatherCity {

    private iModelWeatherCity model;

    public PresenterWeatherCity() {
        model = new ModelWeatherCity(this);
    }

    public void setIdWeather(long idWeather) {
        model.setIdWeather(idWeather);
    }

    @Override
    public void setDataWeather(DataWeather dataWeather) {
        String nameCity = dataWeather.getName();
        int t = (int) dataWeather.getMain().getTemp();
        String temp = t + "";
        getViewState().setData(nameCity, temp);
    }
}