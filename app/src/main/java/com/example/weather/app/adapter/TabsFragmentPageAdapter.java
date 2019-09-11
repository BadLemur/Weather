package com.example.weather.app.adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.weather.MainApp;
import com.example.weather.eventBus.ClickItemRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

public class TabsFragmentPageAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "TabsFragmentPageAdapter";
    @Inject
    Context context;

    private ArrayList<MvpAppCompatFragment> fragmentArrayList = new ArrayList<>();

    public TabsFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        MainApp.app().appComponent().inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Subscribe
    public void eventClickItemRecyclerView(ClickItemRecyclerView event) {
        Log.e(TAG, "eventClickItemRecyclerView: " + event.getIdWeather());
    }


//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return fragmentArrayList.get(position).;
//    }
}