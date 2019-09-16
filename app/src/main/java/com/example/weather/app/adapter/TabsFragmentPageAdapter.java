package com.example.weather.app.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.weather.MainApp;
import com.example.weather.app.fragments.weatherCity.view.WeatherCity;

import java.util.ArrayList;
import java.util.List;

public class TabsFragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<Long> idCityList;

    public TabsFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setListCity(List<Long> idCityList) {
        this.idCityList = idCityList;
        updateListCity();
    }

    public void updateListCity() {
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        WeatherCity city = new WeatherCity();
        Bundle bundle = new Bundle();
        bundle.putLong("idWeather", idCityList.get(position));
        city.setArguments(bundle);
        return city;
    }

    @Override
    public int getCount() {
        if (idCityList == null)
            return 0;
        return idCityList.size();
    }
}