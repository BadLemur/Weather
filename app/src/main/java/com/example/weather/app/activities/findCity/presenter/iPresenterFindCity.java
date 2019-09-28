package com.example.weather.app.activities.findCity.presenter;

import com.example.weather.app.adapter.recyclerViewFindCity.ItemAdapterFindCity;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCity;

import java.util.List;

public interface iPresenterFindCity {

    void setTextSearch(String str);

    void subscribeTextSearch(List<ItemAdapterFindCity> itemAdapterFindCities);

    void setListOldCity(List<OldChoiceCity> listOldCity);

    void getLocation();
}
