package com.example.weather.app.activities.listCity.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.activities.listCity.model.ModelListCity;
import com.example.weather.app.activities.listCity.model.iModelListCity;
import com.example.weather.app.activities.listCity.view.ViewListCity;
import com.example.weather.data.DB.cityUser.CityUser;

import java.util.List;

@InjectViewState
public class PresenterListCity extends MvpPresenter<ViewListCity> implements iPresenterListCity {
    private iModelListCity model;

    public PresenterListCity() {
        model = new ModelListCity(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model.searchListCity();
    }

    private List<CityUser> listCity;

    @Override
    public void setListCity(List<CityUser> listCity) {
        this.listCity = listCity;
    }
}