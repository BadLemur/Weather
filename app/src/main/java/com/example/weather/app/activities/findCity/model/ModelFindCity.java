package com.example.weather.app.activities.findCity.model;

import com.example.weather.app.activities.findCity.presenter.iPresenterFindCity;

public class ModelFindCity implements iModelFindCity {

    private iPresenterFindCity presenter;

    public ModelFindCity(iPresenterFindCity presenter) {
        this.presenter = presenter;
    }


}