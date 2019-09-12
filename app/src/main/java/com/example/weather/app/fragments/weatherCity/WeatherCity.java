package com.example.weather.app.fragments.weatherCity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;

public class WeatherCity extends MvpAppCompatFragment implements ViewWeatherCity{
    @InjectPresenter
    PresenterWeatherCity presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.weather_city, container, false);
        return root;
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