package com.example.weather.app.activities.main.model;

import android.annotation.SuppressLint;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.presenter.iPresenterMainActivity;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.cityUser.CityUserDAO;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelMainActivity implements iModelMainActivity, iOnCreateFirstDB {
    private static final String TAG = "ModelMainActivity";
    @Inject
    CityUserDAO cityUserDAO;

    @Inject
    CityDAO cityDAO;

    private iPresenterMainActivity presenter;

    public ModelMainActivity(iPresenterMainActivity presenter) {
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
                        new OnCreateFirstDB().doParser(this);
                });
    }

    @Override
    public void getCityUser() {
        cityUserDAO.getCityUserDAO()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe((cityUsersList, throwable) -> {
                    presenter.setCityUser(cityUsersList);
                });
    }
}