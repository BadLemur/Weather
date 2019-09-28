package com.example.weather.app.activities.findCity.model.listOldCity;

import android.annotation.SuppressLint;

import com.example.weather.MainApp;
import com.example.weather.app.activities.findCity.presenter.iPresenterFindCity;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCityDAO;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelListOldCity implements iModelListOldCity {
    private iPresenterFindCity presenter;
    @Inject OldChoiceCityDAO oldChoiceCityDAO;

    public ModelListOldCity(iPresenterFindCity presenter) {
        this.presenter = presenter;
        MainApp.app().appComponent().inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void searchOldCity() {
        oldChoiceCityDAO.getList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(listOldCity -> {
                    presenter.setListOldCity(listOldCity);
                });
    }
}
