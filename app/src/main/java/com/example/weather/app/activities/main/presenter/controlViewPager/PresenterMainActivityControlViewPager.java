package com.example.weather.app.activities.main.presenter.controlViewPager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.activities.main.model.doViewModel.DoViewModel;
import com.example.weather.app.activities.main.model.doViewModel.iDoViewModel;
import com.example.weather.app.activities.main.view.ViewMainActivity;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.eventBus.ClickItemRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class PresenterMainActivityControlViewPager extends MvpPresenter<ViewMainActivity> implements iPresenterMainActivityControlViewPager {

    private iDoViewModel modelView;

    public PresenterMainActivityControlViewPager() {
        EventBus.getDefault().register(this);
        modelView = new DoViewModel(this);
    }

    @Override
    public void showCityUser() {
        modelView.doViewCity();
    }

    @Override
    public void setCityUser(List<CityUser> list) {
        if (list.size() > 0) {
            for (CityUser cityUser : list)
                listCity.add(cityUser.idWeather);
            getViewState().updateViewPager();
        } else getViewState().loadFindCity();
    }

    /*если id есть, просто переключает на него,
     если нет, добавляет в список и переключает на него*/
    @Subscribe
    public void eventClickItemRecyclerView(ClickItemRecyclerView event) {
        for (int i = 0; listCity.size() > i; ++i) {
            if (listCity.get(i) == event.getIdWeather()) {
                getViewState().showPositionViewPager(i - 1);
                return;
            }
        }
        modelView.addNewCity(event.getIdWeather());
        listCity.add(event.getIdWeather());
        getViewState().showPositionViewPager(listCity.size() - 1);
    }

    private List<Long> listCity = new ArrayList<>();

    @Override
    public List<Long> getListCity() {
        return listCity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}