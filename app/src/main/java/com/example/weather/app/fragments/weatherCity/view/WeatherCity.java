package com.example.weather.app.fragments.weatherCity.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.fragments.weatherCity.presenter.PresenterWeatherCity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherCity extends MvpAppCompatFragment implements ViewWeatherCity {

    @InjectPresenter
    PresenterWeatherCity presenter;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.tv_connection_to_server) TextView connectionServer;
    @BindView(R.id.tv_name_city) TextView nameCity;
    @BindView(R.id.tv_temp) TextView temp;
    @BindView(R.id.tv_weather) TextView weather;

    @BindView(R.id.image_weather_city) ImageView imageWeather;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.weather_city, container, false);
        ButterKnife.bind(this, root);
        Bundle bundle = getArguments();
        presenter.setIdWeather(bundle.getLong("idWeather"));
        return root;
    }

    @Override
    public void setData(String nameCity, String temp) {
        this.nameCity.setText(nameCity);
        this.temp.setText(temp);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this, root).unbind();
    }
}