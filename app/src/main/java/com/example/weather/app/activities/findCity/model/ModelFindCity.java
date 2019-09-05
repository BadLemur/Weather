package com.example.weather.app.activities.findCity.model;

import android.content.Context;

import com.example.weather.MainApp;
import com.example.weather.app.activities.findCity.presenter.iPresenterFindCity;

import javax.inject.Inject;

public class ModelFindCity implements iModelFindCity {
    @Inject
    Context context;
    private iPresenterFindCity presenter;

    public ModelFindCity(iPresenterFindCity presenter) {
        this.presenter = presenter;
        MainApp.app().appComponent().inject(this);
    }
}