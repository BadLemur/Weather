package com.example.weather.app.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.weather.MainApp;
import com.example.weather.app.fragments.weatherCity.view.WeatherCity;

import java.util.ArrayList;

import javax.inject.Inject;

public class TabsFragmentPageAdapter extends FragmentPagerAdapter {

    @Inject
    Context context;

    private ArrayList<WeatherCity> fragmentArrayList = new ArrayList<>();

    public TabsFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        MainApp.app().appComponent().inject(this);
    }

    public void addFragment(long idWeather) {
        WeatherCity city = new WeatherCity();
        Bundle bundle = new Bundle();
        bundle.putLong("idWeather", idWeather);
        city.setArguments(bundle);
        fragmentArrayList.add(city);
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

}