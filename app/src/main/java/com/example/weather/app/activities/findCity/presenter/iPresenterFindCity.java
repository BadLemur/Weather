package com.example.weather.app.activities.findCity.presenter;

import com.example.weather.app.adapter.recyclerViewFindCity.ItemAdapterFindCity;

import java.util.List;

public interface iPresenterFindCity {

    void setTextSearch(String str);

    void subscribeTextSearch(List<ItemAdapterFindCity> itemAdapterFindCities);
}
