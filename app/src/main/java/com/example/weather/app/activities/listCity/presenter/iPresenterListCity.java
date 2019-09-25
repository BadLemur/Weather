package com.example.weather.app.activities.listCity.presenter;

import com.example.weather.data.DB.cityUser.CityUser;

import java.util.List;

public interface iPresenterListCity {

    void setListCity(List<CityUser> listCity);

    void notifyDataSetChangedAdapter();
}