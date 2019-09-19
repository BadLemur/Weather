package com.example.weather.app.activities.main.model.onFirstStart.doCreateDB;

import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.model.onFirstStart.doCreateDB.parserCity.ParserCity;
import com.example.weather.app.activities.main.model.onFirstStart.doCreateDB.parserCity.iParserCity;
import com.example.weather.app.activities.main.model.onFirstStart.doCreateDB.parserFile.NewParserJson;
import com.example.weather.app.activities.main.model.onFirstStart.doCreateDB.parserFile.iNewParserJson;
import com.example.weather.app.activities.main.model.onFirstStart.iOnFirstStart;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.parser.ParserWeather;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DoCreateDB implements iDoCreateDB {
    private static final String TAG = "DoCreateDB";

    private iNewParserJson parserJson;
    private iParserCity parserCity;
    private iOnFirstStart firstStart;

    @Inject CityDAO cityDAO;

    public DoCreateDB(iOnFirstStart firstStart) {
        MainApp.app().appComponent().inject(this);
        parserJson = new NewParserJson();
        parserCity = new ParserCity();
        this.firstStart = firstStart;
    }

    @Override
    public Disposable createDB() {
        return null;
//        return parserJson.returnListTempWeather()
//                .subscribeOn(Schedulers.io())
//                .concatMap((Function<List<ParserWeather>, Observable<List<City>>>) parserWeathers -> {
//                    return parserCity.getListObservable(parserWeathers);
//                })
//                .doOnNext(cityList -> cityDAO.addAll(cityList))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(cityList -> firstStart.onComplete(),
//                        throwable -> Log.e(TAG, "createDB: ", throwable));
    }
}