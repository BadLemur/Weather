package com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parser.NewParserJson;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parser.iNewParserJson;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.iOnFirstStart;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.parser.ParserWeather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
        Observable<List<ParserWeather>> observable = parserJson.returnListTempWeather();

        observable
                .subscribeOn(Schedulers.io())
                .concatMap((Function<List<ParserWeather>, Observable<List<City>>>) parserWeathers -> {
                    Observable<List<City>> listFlowable = Observable.create(emitter -> {
                        List<City> cityList = new ArrayList<>();
                        for (int index = 0; parserWeathers.size() > index; ++index) {
                            ParserWeather weather = parserWeathers.get(index);
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
                        cityDAO.addAll(cityList);
                        emitter.onNext(cityList);

                    });

                    return listFlowable;
                })
                .concatMap(new Function<List<City>, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(List<City> cityList) throws Exception {
                        Observable<Integer> integerFlowable = Observable.create(emitter ->
                                emitter.onNext(cityList.size()));
                        return integerFlowable;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
//                        Log.e(TAG, "onNext: " + integer);
//                        firstStart.onProgress(integer);
                        firstStart.onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        firstStart.onComplete();
                    }
                });
    }
}