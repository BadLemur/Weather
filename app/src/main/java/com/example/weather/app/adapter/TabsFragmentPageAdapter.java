package com.example.weather.app.adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.weather.MainApp;
import com.example.weather.app.fragments.weatherCity.WeatherCity;
import com.example.weather.eventBus.ClickItemRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
        fragmentArrayList.add(new WeatherCity());
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


//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return fragmentArrayList.get(position).;
//    }
}