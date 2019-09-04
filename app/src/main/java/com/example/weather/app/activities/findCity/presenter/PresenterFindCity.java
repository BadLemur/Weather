package com.example.weather.app.activities.findCity.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.activities.findCity.model.ModelFindCity;
import com.example.weather.app.activities.findCity.model.iModelFindCity;
import com.example.weather.app.activities.findCity.view.ViewFindCity;

public class PresenterFindCity extends MvpPresenter<ViewFindCity> implements iPresenterFindCity {
    private iModelFindCity model;

    public PresenterFindCity() {
        model = new ModelFindCity(this);
    }

    @Override
    public void attachView(ViewFindCity view) {
        super.attachView(view);
    }
}