package com.example.weather.app.activities.main.presenter.loader;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.activities.main.model.addOldChoiceCity.AddOldChoiceCity;
import com.example.weather.app.activities.main.model.addOldChoiceCity.iAddOldChoiceCity;
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

    private iOnFirstStart firstStart;
    private iAddOldChoiceCity addOldChoiceCity;

    public PresenterMainActivityLoader() {
        firstStart = new OnFirstStart(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        firstStart.onFirstStart();
        addOldChoiceCity = new AddOldChoiceCity();
    }

    @Override
    public void onCreatedDB() {
        getViewState().doLoader();
        getViewState().showViewPager();
    }
}