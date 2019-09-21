package com.example.weather.app.activities.main.model.onFirstStart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.presenter.iPresenterMainActivity;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.parser.ParserWeather;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OnFirstStartModel implements iOnFirstStartModel {
    private static final String TAG = "OnFirstStartModel";

    @Inject CityDAO cityDAO;
    @Inject Context context;

    private iPresenterMainActivity presenter;

    public OnFirstStartModel(iPresenterMainActivity presenter) {
        MainApp.app().appComponent().inject(this);
        this.presenter = presenter;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onFirstStart() {
        cityDAO.getCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    if (integer == 0)
                        createDB();
                    else
                        presenter.onCreatedDB();
                });
    }

    @SuppressLint("CheckResult")
    private void createDB() {
        parserDB()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<ParserWeather>, Single<List<City>>>() {
                    @Override
                    public Single<List<City>> apply(List<ParserWeather> parserWeathers) throws Exception {
                        return listSingleCity(parserWeathers);
                    }
                })
                .doOnSuccess(cityList -> cityDAO.addAll(cityList))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityList ->
                                presenter.onCreatedDB(),
                        throwable -> Log.e(TAG, "createDB: ", throwable));
    }

    private Single<List<ParserWeather>> parserDB() {

        Single<List<ParserWeather>> single = Single.create(emitter -> {
            String strJson;
            AssetManager assetManager = context.getAssets();
            InputStream stream = assetManager.open("city.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();

            strJson = new String(buffer, "UTF-8");
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(strJson);
            JsonArray jsonArray = (JsonArray) obj;
            List<ParserWeather> weatherList = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; jsonArray.size() > i; ++i) {
                weatherList.add(gson.fromJson(jsonArray.get(i), ParserWeather.class));
            }
            emitter.onSuccess(weatherList);
        });
        return single;
    }

    private Single<List<City>> listSingleCity(List<ParserWeather> parserWeathers) {

        Single<List<City>> single = Single.create(emitter -> {
            List<City> cityList = new ArrayList<>();
            for (int index = 0; parserWeathers.size() > index; ++index) {
                ParserWeather weather = parserWeathers.get(index);
                City city = City.builder()
                        .idWeather(weather.getId())
                        .country(weather.getCountry())
                        .cityEN(weather.getNameCity())
                        .build();
                cityList.add(city);
            }
            emitter.onSuccess(cityList);
        });
        return single;
    }

}