package com.example.weather.app.fragments.weatherCity.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.fragments.weatherCity.presenter.PresenterWeatherCity;

import javax.annotation.Resources;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WeatherCity extends MvpAppCompatFragment implements ViewWeatherCity {

    private Typeface typeface;

    @InjectPresenter
    PresenterWeatherCity presenter;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.tv_connection_to_server) TextView connectionServer;
    @BindView(R.id.tv_name_city) TextView nameCity;
    @BindView(R.id.tv_temp) TextView temp;
    @BindView(R.id.tv_weather) TextView weather;

    @BindView(R.id.tv_image_weather_city) TextView imageWeather;
    private View root;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.weather_city, container, false);
        unbinder = ButterKnife.bind(this, root);

        Bundle bundle = getArguments();
        presenter.setIdWeather(bundle.getLong("idWeather"));

        typeface = ResourcesCompat.getFont(getContext(), R.font.font);
        imageWeather.setTypeface(typeface);
        return root;
    }

    @Override
    public void setData(String nameCity, String temp, String typeWeather, String typeWeatherIcon) {
        this.nameCity.setText(nameCity);
        this.temp.setText(temp);
        weather.setText(typeWeather);
        imageWeather.setText(typeWeatherIcon);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}