package com.example.weather.app.activities.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.activities.main.model.doViewModel.DoViewModel;
import com.example.weather.app.activities.main.model.doViewModel.iDoViewModel;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.OnFirstStart;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.iOnFirstStart;
import com.example.weather.app.activities.main.view.ViewMainActivity;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.eventBus.ClickItemRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

@InjectViewState
public class PresenterMainActivity extends MvpPresenter<ViewMainActivity> implements iPresenterMainActivity {
    private static final String TAG = "PresenterMainActivity";

    private iDoViewModel modelView;
    private iOnFirstStart firstStart;

    public PresenterMainActivity() {
        modelView = new DoViewModel(this);
        firstStart = new OnFirstStart(this);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        firstStart.onFirstStart();
    }

    @Override
    public void onCreatedDB() {
        getViewState().doLoader();
    }

    @Override
    public void setCityUser(List<CityUser> list) {
        if (list.size() > 0) {
            for (CityUser cityUser : list)
                getViewState().addCity(cityUser.idWeather);
        } else getViewState().loadFindCity();
    }

    @Subscribe
    public void eventClickItemRecyclerView(ClickItemRecyclerView event) {
        modelView.addNewCity(event.getIdWeather());
        getViewState().addCity(event.getIdWeather());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}