package com.example.weather.app.activities.findCity.presenter;

import com.example.weather.app.adapter.recyclerView.ItemAdapterFindCity;

import java.util.List;

public interface iPresenterFindCity {

//    void setTextSearch(Observable<String> observable);

    void setTextSearch(String str);

    void subscribeTextSearch(List<ItemAdapterFindCity> itemAdapterFindCities);
}
