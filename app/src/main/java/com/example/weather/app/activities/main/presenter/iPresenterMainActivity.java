package com.example.weather.app.activities.main.presenter;

import com.example.weather.data.DB.cityUser.CityUser;

import java.util.List;

public interface iPresenterMainActivity {

    void onCreatedDB();

    List<Long> getListCity();

    void setCityUser(List<CityUser> list);
}