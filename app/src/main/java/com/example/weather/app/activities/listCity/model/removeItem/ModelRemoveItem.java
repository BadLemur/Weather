package com.example.weather.app.activities.listCity.model.removeItem;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.app.activities.listCity.presenter.iPresenterListCity;
import com.example.weather.data.DB.cityUser.CityUserDAO;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelRemoveItem implements iModelRemoveItem {
    private static final String TAG = "ModelRemoveItem";
    @Inject CityUserDAO cityUserDAO;
    private iPresenterListCity presenter;

    public ModelRemoveItem(iPresenterListCity presenter) {
        this.presenter = presenter;
        MainApp.app().appComponent().inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void removeItemCityUser(long idWeather) {
        Completable.fromAction(() -> {
            cityUserDAO.delete(idWeather);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> presenter.notifyDataSetChangedAdapter(),
                        throwable -> Log.e(TAG, "removeItemCityUser: ", throwable));
    }
}