package com.example.weather.app.activities.main.presenter.loader;

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
public class PresenterMainActivityLoader extends MvpPresenter<ViewMainActivity> implements iPresenterMainActivityLoader {
    private static final String TAG = "PresenterMainActivityLoader";

//    private iDoViewModel modelView;
    private iOnFirstStart firstStart;

    public PresenterMainActivityLoader() {
//        modelView = new DoViewModel(this);
        firstStart = new OnFirstStart(this);

//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        firstStart.onFirstStart();
    }

    @Override
    public void onCreatedDB() {
        getViewState().doLoader();
//        modelView.doViewCity();
    }



//    @Subscribe
//    public void eventClickItemRecyclerView(ClickItemRecyclerView event) {
//        modelView.addNewCity(event.getIdWeather());
//        getViewState().addCity(event.getIdWeather());
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
}