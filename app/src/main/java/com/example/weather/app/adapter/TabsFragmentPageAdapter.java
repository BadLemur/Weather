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

    private List<Long> idCityList = new ArrayList<>();

    public TabsFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addIdCity(long idWeather) {
        idCityList.add(idWeather);
        this.notifyDataSetChanged();
    }

    public void removeIdCity(long idWeather) {
        for (int i = 0; idCityList.size() > i; ++i)
            if (idCityList.get(i) == idWeather) {
                idCityList.remove(i);
                break;
            }
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
        return idCityList.size();
    }
}