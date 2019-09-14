package com.example.weather.app.activities.main.model.doCreateDB;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.presenter.iPresenterMainActivity;
import com.example.weather.data.DB.city.CityDAO;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelLoader implements iModelLoader, CallbackOnCreateDB {
    private static final String TAG = "ModelLoader";
    @Inject
    CityDAO cityDAO;

    private iPresenterMainActivity presenter;

    public ModelLoader(iPresenterMainActivity presenter) {
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
                    Log.e(TAG, "onFirstStart: " + integer);
                    if (integer == 0)
                        new CreateFirstDB().doParser(this);
                    else onCreatedDB();
                }, throwable -> Log.e(TAG, "onFirstStart: ", throwable));
    }

    @Override
    public void onCreatedDB() {
        presenter.onCreatedDB();
    }
}