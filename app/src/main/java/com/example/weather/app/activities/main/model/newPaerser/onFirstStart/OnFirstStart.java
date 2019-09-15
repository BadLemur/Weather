package com.example.weather.app.activities.main.model.newPaerser.onFirstStart;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.DoCreateDB;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.iDoCreateDB;
import com.example.weather.app.activities.main.presenter.iPresenterMainActivity;
import com.example.weather.data.DB.city.CityDAO;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OnFirstStart implements iOnFirstStart {
    private static final String TAG = "OnFirstStart";
    @Inject
    CityDAO cityDAO;

    private iPresenterMainActivity presenter;

    public OnFirstStart(iPresenterMainActivity presenter) {
        MainApp.app().appComponent().inject(this);
        this.presenter = presenter;
    }

    private Disposable disposable;

    @SuppressLint("CheckResult")
    @Override
    public void onFirstStart() {
        cityDAO.getCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    Log.e(TAG, "OnFirstStart: " + integer);
                    if (integer == 0) {
                        iDoCreateDB doCreateDB = new DoCreateDB(this);
                        disposable = doCreateDB.createDB();
                    } else presenter.onCreatedDB();
                }, throwable -> Log.e(TAG, "OnFirstStart: ", throwable));
    }

    @Override
    public void onComplete() {
        presenter.onCreatedDB();
        disposable.dispose();
    }
}