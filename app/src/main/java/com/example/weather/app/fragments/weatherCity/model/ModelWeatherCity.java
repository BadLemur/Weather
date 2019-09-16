package com.example.weather.app.fragments.weatherCity.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.R;
import com.example.weather.app.fragments.weatherCity.presenter.iPresenterWeatherCity;
import com.example.weather.data.network.Api;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelWeatherCity implements iModelWeatherCity {
    private static final String TAG = "ModelWeatherCity";
    private iPresenterWeatherCity presenter;
    @Inject Context context;

    public ModelWeatherCity(iPresenterWeatherCity presenter) {
        this.presenter = presenter;
        MainApp.app().appComponent().inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void setIdWeather(long idWeather) {
        Api api = Api.Instance.getApi();
        String appid = context.getResources().getString(R.string.appid);
        String units = context.getResources().getString(R.string.units);

        api.getDataWeatherByCity(idWeather, appid, units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataWeather -> {
                    presenter.setDataWeather(dataWeather);
                }, throwable -> Log.e(TAG, "setIdWeather: " + throwable));
    }
}