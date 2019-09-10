package com.example.weather.app.activities.main.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.activities.main.model.doCreateDB.ModelLoader;
import com.example.weather.app.activities.main.model.doViewModel.DoViewModel;
import com.example.weather.app.activities.main.model.doViewModel.iDoViewModel;
import com.example.weather.app.activities.main.view.ViewMainActivity;
import com.example.weather.app.activities.main.model.doCreateDB.iModelLoader;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.eventBus.ClickItemRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

@InjectViewState
public class PresenterMainActivity extends MvpPresenter<ViewMainActivity> implements iPresenterMainActivity {
    private static final String TAG = "PresenterMainActivity";
    private iModelLoader modelLoader;
    private iDoViewModel modelView;


    public PresenterMainActivity() {
        modelLoader = new ModelLoader(this);
        modelView = new DoViewModel(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        modelLoader.onFirstStart();
    }

    @Override
    public void onCreatedDB() {
        getViewState().doLoader();
        modelView.doViewCity();
    }

    @Override
    public void setCityUser(List<CityUser> list) {
        if (list.size() > 0) {
        } else getViewState().loadFindCity();
    }

    @Subscribe
    public void eventClickItemRecyclerView(ClickItemRecyclerView event) {
        Log.e(TAG, "eventClickItemRecyclerView: " +event.getIdWeather());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}