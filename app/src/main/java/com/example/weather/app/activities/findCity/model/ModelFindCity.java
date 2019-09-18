package com.example.weather.app.activities.findCity.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.app.activities.findCity.presenter.iPresenterFindCity;
import com.example.weather.app.adapter.recyclerView.ItemAdapterFindCity;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

public class ModelFindCity implements iModelFindCity {
    private static final String TAG = "ModelFindCity";
    @Inject
    CityDAO cityDAO;

    private iPresenterFindCity presenter;
    @Getter
    private List<ItemAdapterFindCity> findCityList = new ArrayList<>();
    private ObservableEmitter<String> observableEmitter;
    private Disposable disposable;

    @SuppressLint("CheckResult")
    public ModelFindCity(iPresenterFindCity presenter) {
        this.presenter = presenter;
        MainApp.app().appComponent().inject(this);

        Observable.create((ObservableOnSubscribe<String>)
                emitter -> observableEmitter = emitter)
                .map(s -> s.trim().toLowerCase().trim())
                .debounce(250, TimeUnit.MILLISECONDS)
                .filter(s -> !s.isEmpty())
                .distinctUntilChanged()
                .flatMap(s -> cityDAO.getList(s + "%"))
                .map(cityList -> {
                    findCityList.clear();
                    for (City city : cityList) {
                        ItemAdapterFindCity item = ItemAdapterFindCity
                                .builder()
                                .idWeather(city.idWeather)
                                .country(city.country)
                                .build();
                        if (city.getCityEN() != null)
                            item.setNameCityEN(city.getCityEN());
                        findCityList.add(item);
                    }
                    return findCityList;
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemAdapterFindCities ->
                                presenter.subscribeTextSearch(itemAdapterFindCities),
                        throwable -> Log.e(TAG, "ModelFindCity: ", throwable),
                        () -> {
                        }, dispos -> disposable = dispos);
    }

    @Override
    public void setTextSearch(String str) {
        observableEmitter.onNext(str);
    }

    @Override
    public void disposable() {
        disposable.dispose();
    }
}