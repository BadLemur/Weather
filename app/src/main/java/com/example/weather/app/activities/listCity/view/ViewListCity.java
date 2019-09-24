package com.example.weather.app.activities.listCity.view;

import com.arellomobile.mvp.MvpView;
import com.example.weather.app.adapter.recyclerViewListCity.AdapterListCityItem;

import java.util.List;

public interface ViewListCity extends MvpView {
    void setListAdapter(List<AdapterListCityItem> list);
}
