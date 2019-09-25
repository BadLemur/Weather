package com.example.weather.app.activities.listCity.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.activities.listCity.model.findItem.ModelFindListCity;
import com.example.weather.app.activities.listCity.model.findItem.iModelFindListCity;
import com.example.weather.app.activities.listCity.model.removeItem.ModelRemoveItem;
import com.example.weather.app.activities.listCity.model.removeItem.iModelRemoveItem;
import com.example.weather.app.activities.listCity.view.ViewListCity;
import com.example.weather.app.adapter.recyclerViewListCity.AdapterListCityItem;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.eventBus.RemoveItemCityUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class PresenterListCity extends MvpPresenter<ViewListCity> implements iPresenterListCity {
    private iModelFindListCity modelFind;
    private iModelRemoveItem modelRemoveItem;

    public PresenterListCity() {
        this.modelFind = new ModelFindListCity(this);
        this.modelRemoveItem = new ModelRemoveItem(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        modelFind.searchListCity();
    }

    private List<AdapterListCityItem> listCityItems = new ArrayList<>();

    @Override
    public void setListCity(List<CityUser> listCity) {
        for (CityUser cityUser : listCity)
            listCityItems.add(new AdapterListCityItem(cityUser.idWeather, cityUser.nameCity));
        getViewState().setListAdapter(listCityItems);
    }

    @Subscribe
    public void removeItemCityUser(RemoveItemCityUser event) {
        modelRemoveItem.removeItemCityUser(event.getIdWeather());
        for (AdapterListCityItem cityItem : listCityItems)
            if (cityItem.getIdWeather() == event.getIdWeather()) {
                listCityItems.remove(cityItem);
                return;
            }
    }

    @Override
    public void notifyDataSetChangedAdapter() {
        getViewState().notifyDataSetChangedAdapter();
    }

    @Override
    public void destroyView(ViewListCity view) {
        super.destroyView(view);
        EventBus.getDefault().unregister(this);
    }
}