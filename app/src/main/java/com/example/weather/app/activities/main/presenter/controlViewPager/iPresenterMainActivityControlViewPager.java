package com.example.weather.app.activities.main.presenter.controlViewPager;

import com.example.weather.data.DB.cityUser.CityUser;

import java.util.List;

public interface iPresenterMainActivityControlViewPager {

    void showCityUser();

    void setCityUser(List<CityUser> list);
}
