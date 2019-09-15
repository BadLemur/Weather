package com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parser.NewParserJson;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parser.iNewParserJson;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.iOnFirstStart;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.parser.ParserWeather;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DoCreateDB implements iDoCreateDB {
    private static final String TAG = "DoCreateDB";
    private iNewParserJson parserJson;
    private iOnFirstStart firstStart;

    @Inject CityDAO cityDAO;

    public DoCreateDB(iOnFirstStart firstStart) {
        MainApp.app().appComponent().inject(this);
        parserJson = new NewParserJson();
        this.firstStart = firstStart;
    }

    @Override
    public void createDB() {
        setFlowable(parserJson.returnListTempWeather());
    }

    @SuppressLint("CheckResult")
    @Override
    public void setFlowable(Flowable<List<ParserWeather>> flowable) {
        flowable
                .subscribeOn(Schedulers.io())
                .concatMap((Function<List<ParserWeather>, Flowable<List<City>>>) parserWeathers -> {
                    Flowable<List<City>> listFlowable = Flowable.create(emitter -> {
                        List<City> cityList = new ArrayList<>();
                        for (int index = 0; parserWeathers.size() > index; ++index) {
                            ParserWeather weather = parserWeathers.get(index);
//                        for (ParserWeather weather : parserWeathers)
                            City city = City.builder()
                                    .idWeather(weather.getId())
                                    .country(weather.getCountry())
                                    .cityEN(weather.getNameCity())
                                    .build();
                            if (weather.getLangs() != null)
                                for (int i = 0; weather.getLangs().size() > i; ++i) {
                                    Map<String, String> map = weather.getLangs().get(i);
                                    if (map.containsKey("ru")) {
                                        city.setCityRU(map.get("ru"));
                                        city.setCityRUToLower(map.get("ru").toLowerCase());
                                    }
                                }
                            cityList.add(city);
                        }
                        Log.e(TAG, "apply: " + "block");
//                        cityDAO.addAll(cityList);
                        emitter.onNext(cityList);

                    }, BackpressureStrategy.BUFFER);

                    return listFlowable;
                })
                .subscribeOn(Schedulers.io())
                .concatMap(new Function<List<City>, Flowable<Integer>>() {
                    @Override
                    public Flowable<Integer> apply(List<City> cityList) throws Exception {
                        Flowable<Integer> integerFlowable = Flowable.create(emitter ->
                                emitter.onNext(cityList.size()), BackpressureStrategy.BUFFER);
                        return integerFlowable;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "onNext: " + integer);
                        firstStart.onProgress(integer);
//                        ter.setProgress(integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        firstStart.onComplete();
//                        presenter.onCreatedDB();
                    }
                });
    }
}