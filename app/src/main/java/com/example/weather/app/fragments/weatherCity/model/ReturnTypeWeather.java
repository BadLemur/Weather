package com.example.weather.app.fragments.weatherCity.model;

import android.content.Context;

import com.example.weather.MainApp;
import com.example.weather.R;

import java.util.Date;

import javax.inject.Inject;

public class ReturnTypeWeather implements iReturnTypeWeather {
    @Inject Context context;

    public ReturnTypeWeather() {
        MainApp.app().appComponent().inject(this);
    }

    @Override
    public String getTypeWeather(int idType, long sunrise, long sunset) {
        int id = idType / 100;
        String icon = "";
        if (idType == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset)
                icon = context.getString(R.string.weather_sunny);
            else
                icon = context.getString(R.string.weather_clear_night);
        } else {
            switch (id) {
                case 2:
                    icon = context.getString(R.string.weather_thunder);
                    break;
                case 3:
                    icon = context.getString(R.string.weather_drizzle);
                    break;
                case 7:
                    icon = context.getString(R.string.weather_foggy);
                    break;
                case 8:
                    icon = context.getString(R.string.weather_cloudy);
                    break;
                case 6:
                    icon = context.getString(R.string.weather_snowy);
                    break;
                case 5:
                    icon = context.getString(R.string.weather_rainy);
                    break;
            }
        }
        return icon;
    }
}