package com.example.weather.app.activities.main.model;

import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.parser.CallbackParserData;
import com.example.weather.data.DB.parser.ParserCity;
import com.example.weather.data.DB.parser.ParserWeather;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OnCreateFirstDB implements CallbackParserData {
    private static final String TAG = "OnCreateFirstDB";
    @Inject
    CityDAO cityDAO;

    private OnCreateFirstDB onCreateFirstDB;

    private iOnCreateFirstDB iOnCreateFirstDB;

    public OnCreateFirstDB() {
        MainApp.app().appComponent().inject(this);
        onCreateFirstDB = this;
    }

    public void doParser(iOnCreateFirstDB iOnCreateFirstDB) {
        this.iOnCreateFirstDB = iOnCreateFirstDB;

        Completable.fromAction(() -> {
            new ParserCity(onCreateFirstDB);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void returnListTempWeather(List<ParserWeather> weatherList) {
        Completable.fromAction(() -> {
            List<City> cityList = new ArrayList<>();
            {
                for (ParserWeather weather : weatherList) {
                    Log.d(TAG, "returnListTempWeather: " + weather.getLangs().get(0));
                    cityList.add(City
                            .builder()
                            .idWeather(weather.getId())
                            .country(weather.getCountry())
                            .cityRU(weather.getLangs().get(0).getRu())
                            .cityRUToLower(weather.getLangs().get(0).getRu().toLowerCase())
                            .cityEN(weather.getLangs().get(0).getEn())
                            .build());

                }
            }
            cityDAO.addAll(cityList);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
