package com.example.weather.app.activities.main.model;

import android.annotation.SuppressLint;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.presenter.iPresenterMainActivity;
import com.example.weather.data.DB.cityUser.CityUserDAO;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelMainActivity implements iModelMainActivity {
    private static final String TAG = "ModelMainActivity";
    @Inject
    CityUserDAO cityUserDAO;

    private iPresenterMainActivity presenter;


    public ModelMainActivity(iPresenterMainActivity presenter) {
        this.presenter = presenter;
        MainApp.app().appComponent().inject(this);
    }

    @SuppressLint("CheckResult")
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