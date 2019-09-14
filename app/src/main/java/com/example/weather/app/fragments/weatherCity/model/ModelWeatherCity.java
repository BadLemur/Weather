package com.example.weather.app.fragments.weatherCity.model;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.weather.MainApp;
import com.example.weather.R;
import com.example.weather.app.fragments.weatherCity.presenter.iPresenterWeatherCity;
import com.example.weather.data.network.Api;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ModelWeatherCity implements iModelWeatherCity {
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

        api.getDataWeatherByCity("Moscow", appid, units)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataWeather -> {
//                    Toast.makeText(getApplicationContext(), dataWeather.get(0).getName() + " "
//                            + dataWeather.get(0).getMain().getTemp(), Toast.LENGTH_LONG).show();
                });
    }
}


//        Api api = Api.Instance.getApi();
//        String appid = this.getResources().getString(R.string.appid);
//        String units = this.getResources().getString(R.string.units);
//
//        api.getDataWeatherByCity("Moscow", appid, units)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(dataWeather -> {
////                    Toast.makeText(getApplicationContext(), dataWeather.get(0).getName() + " "
////                            + dataWeather.get(0).getMain().getTemp(), Toast.LENGTH_LONG).show();
//                });
//
////        tabLayout.addTab(tabLayout.newTab().setText("Васька"));
////        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_fiber_manual_record_black_24dp));