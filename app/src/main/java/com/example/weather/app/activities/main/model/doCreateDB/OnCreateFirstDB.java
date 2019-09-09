package com.example.weather.app.activities.main.model.doCreateDB;

import android.annotation.SuppressLint;

import com.example.weather.MainApp;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.parser.CallbackParserData;
import com.example.weather.data.DB.parser.ParserJson;
import com.example.weather.data.DB.parser.ParserWeather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OnCreateFirstDB implements CallbackParserData, iOnCreateFirstDB {

    @Inject
    CityDAO cityDAO;

    private OnCreateFirstDB onCreateFirstDB;

    private CallbackOnCreateDB callbackOnCreateDB;

    public OnCreateFirstDB() {
        MainApp.app().appComponent().inject(this);
        onCreateFirstDB = this;
    }

    @Override
    public void doParser(CallbackOnCreateDB callbackOnCreateDB) {
        this.callbackOnCreateDB = callbackOnCreateDB;

        Completable.fromAction(() -> {
            new ParserJson(onCreateFirstDB);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @SuppressLint("CheckResult")
    @Override
    public void returnListTempWeather(List<ParserWeather> weatherList) {
        Completable.fromAction(() -> {
            List<City> cityList = new ArrayList<>();
            {
                for (ParserWeather weather : weatherList) {
                    if (weather.getLangs() != null) {
                        City city = City.builder()
                                .idWeather(weather.getId())
                                .country(weather.getCountry())
                                .build();
                        for (int i = 0; weather.getLangs().size() > i; ++i) {
                            Map<String, String> map = weather.getLangs().get(i);
                            if (map.get("ru") != null || map.get("en") != null) {

                                if (map.containsKey("ru")) {
                                    city.setCityRU(map.get("ru"));
                                    city.setCityRUToLower(map.get("ru").toLowerCase());
                                }
                                if (map.containsKey("en"))
                                    city.setCityEN(map.get("en"));
                            }
                        }
                        if (city.getCityEN() != null || city.getCityRU() != null)
                            cityList.add(city);
                    }
                }
            }
            cityDAO.addAll(cityList);
            cityList.clear();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> callbackOnCreateDB.onCreatedDB());
    }
}