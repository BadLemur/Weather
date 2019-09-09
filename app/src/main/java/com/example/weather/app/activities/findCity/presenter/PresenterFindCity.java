package com.example.weather.app.activities.findCity.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.MainApp;
import com.example.weather.app.activities.findCity.model.ModelFindCity;
import com.example.weather.app.activities.findCity.model.iModelFindCity;
import com.example.weather.app.activities.findCity.view.ViewFindCity;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PresenterFindCity extends MvpPresenter<ViewFindCity> implements iPresenterFindCity {
    @Inject
    CityDAO cityDAO;
    private iModelFindCity model;

    public PresenterFindCity() {
        MainApp.app().appComponent().inject(this);
        model = new ModelFindCity(this);
    }

    private Disposable disposable;
    private List<City> list = new ArrayList<>();

    @SuppressLint("CheckResult")
    @Override
    public void searchViewObservable(Observable<String> observable) {
        observable
                .subscribeOn(Schedulers.io())
                .map(s -> s.trim().toLowerCase())
                .debounce(250, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .flatMap((Function<String, Observable<List<City>>>)
                        s -> cityDAO.getFindToLike("%" + s + "%"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCity -> {
                    list.clear();
                    for (City i : listCity) {
                        City city = City
                                .builder()
                                .idWeather(i.idWeather)
                                .country(i.country)
                                .build();
                        if (i.getCityRU() != null)
                            city.setCityEN(i.getCityEN());
                        if (i.getCityRU() != null)
                            city.setCityRU(i.getCityRU());
                        listCity.add(city);
                    }
                    getViewState().updateRecyclerView();
                }, throwable -> {
                }, () -> {
                }, disposable -> {
                    this.disposable = disposable;
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}