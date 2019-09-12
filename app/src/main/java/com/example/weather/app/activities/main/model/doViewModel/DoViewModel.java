package com.example.weather.app.activities.main.model.doViewModel;

import android.annotation.SuppressLint;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.presenter.iPresenterMainActivity;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.data.DB.cityUser.CityUserDAO;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DoViewModel implements iDoViewModel {
    private static final String TAG = "DoViewModel";
    @Inject
    CityUserDAO cityUserDAO;

    private iPresenterMainActivity presenter;

    public DoViewModel(iPresenterMainActivity presenter) {
        MainApp.app().appComponent().inject(this);
        this.presenter = presenter;
    }

    @SuppressLint("CheckResult")
    @Override
    public void doViewCity() {
        cityUserDAO.getCityUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((cityUsersList, throwable) -> {
                    presenter.setCityUser(cityUsersList);
                });
    }

    @Override
    public void addNewCity(long idWearer) {
        Completable.fromAction(() -> {
            cityUserDAO.add(new CityUser(idWearer));
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}