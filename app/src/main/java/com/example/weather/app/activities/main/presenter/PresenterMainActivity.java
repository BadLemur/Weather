package com.example.weather.app.activities.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.app.activities.main.view.ViewMainActivity;
import com.example.weather.app.activities.main.model.ModelMainActivity;
import com.example.weather.app.activities.main.model.iModelMainActivity;
import com.example.weather.data.DB.cityUser.CityUser;

import java.util.List;

@InjectViewState
public class PresenterMainActivity extends MvpPresenter<ViewMainActivity> implements iPresenterMainActivity {
    private iModelMainActivity model;

    public PresenterMainActivity() {
        model = new ModelMainActivity(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model.onFirstStart();
    }

    @Override
    public void setCityUser(List<CityUser> list) {
        if (list.size() > 0) {

        } else getViewState().loaderFindCity();
    }
}