package com.example.weather.app.activities.findCity.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.MainApp;
import com.example.weather.app.activities.findCity.model.ModelFindCity;
import com.example.weather.app.activities.findCity.model.iModelFindCity;
import com.example.weather.app.activities.findCity.view.ViewFindCity;
import com.example.weather.app.adapter.recyclerView.ItemAdapterFindCity;

import java.util.List;

@InjectViewState
public class PresenterFindCity extends MvpPresenter<ViewFindCity> implements iPresenterFindCity {
    private iModelFindCity model;

    public PresenterFindCity() {
        MainApp.app().appComponent().inject(this);
        model = new ModelFindCity(this);
    }

    @Override
    public void setTextSearch(String str) {
        model.setTextSearch(str);
    }

    @Override
    public void subscribeTextSearch(List<ItemAdapterFindCity> itemAdapterFindCities) {
        getViewState().updateRecyclerView(itemAdapterFindCities);
    }

    public void disposable() {
        model.disposable();
    }
}