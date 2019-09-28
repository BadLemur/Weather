package com.example.weather.app.activities.findCity.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.MainApp;
import com.example.weather.app.activities.findCity.model.listOldCity.ModelListOldCity;
import com.example.weather.app.activities.findCity.model.listOldCity.iModelListOldCity;
import com.example.weather.app.activities.findCity.model.searchCity.ModelFindCity;
import com.example.weather.app.activities.findCity.model.searchCity.iModelFindCity;
import com.example.weather.app.activities.findCity.view.ViewFindCity;
import com.example.weather.app.adapter.recyclerViewFindCity.ItemAdapterFindCity;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCity;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class PresenterFindCity extends MvpPresenter<ViewFindCity> implements iPresenterFindCity {
    private iModelFindCity modelFindCity;
    private iModelListOldCity modelListOldCity;

    private List<ItemAdapterFindCity> itemAdapterFindCities = new ArrayList<>();
    private Boolean isVisible = false;

    public PresenterFindCity() {
        MainApp.app().appComponent().inject(this);
        modelFindCity = new ModelFindCity(this);
        modelListOldCity = new ModelListOldCity(this);
    }

    @Override
    public void attachView(ViewFindCity view) {
        super.attachView(view);
        modelListOldCity.searchOldCity();
        getViewState().setList(itemAdapterFindCities);
    }

    @Override
    public void setListOldCity(List<OldChoiceCity> listOldCity) {
        for (OldChoiceCity oldChoiceCity : listOldCity)
            getViewState().createListOldCity(oldChoiceCity);
    }

    @Override
    public void setTextSearch(String str) {
        modelFindCity.setTextSearch(str);
    }


    @Override
    public void subscribeTextSearch(List<ItemAdapterFindCity> itemAdapterFindCities) {
        this.itemAdapterFindCities.clear();
        this.itemAdapterFindCities.addAll(itemAdapterFindCities);
        if (!isVisible) {
            getViewState().setVisible();
            isVisible = true;
        }
        getViewState().updateRecyclerView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        modelFindCity.disposable();
    }

    @Override
    public void getLocation() {

    }
}