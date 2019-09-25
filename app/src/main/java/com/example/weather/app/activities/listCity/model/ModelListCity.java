package com.example.weather.app.activities.listCity.model;

import android.annotation.SuppressLint;

import com.example.weather.MainApp;
import com.example.weather.app.activities.listCity.presenter.iPresenterListCity;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.data.DB.cityUser.CityUserDAO;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ModelListCity implements iModelListCity {

    @Inject CityUserDAO cityUserDAO;
    private iPresenterListCity presenter;

    public ModelListCity(iPresenterListCity presenter) {
        this.presenter = presenter;
        MainApp.app().appComponent().inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void searchListCity() {
        cityUserDAO.getCityUser()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<CityUser>, SingleSource<City>>() {
                    @Override
                    public SingleSource<City> apply(List<CityUser> cityUsers) throws Exception {
                        return null;
                    }
                });
//                .subscribe(cityUsers -> presenter.setListCity(cityUsers));
    }
}