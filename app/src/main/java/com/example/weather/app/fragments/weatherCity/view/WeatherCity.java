package com.example.weather.app.fragments.weatherCity.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.fragments.weatherCity.presenter.PresenterWeatherCity;

public class WeatherCity extends MvpAppCompatFragment implements ViewWeatherCity {
    @InjectPresenter
    PresenterWeatherCity presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.weather_city, container, false);
        Bundle bundle = getArguments();
        presenter.setIdWeather(bundle.getLong("idWeather"));
        return root;
    }
}