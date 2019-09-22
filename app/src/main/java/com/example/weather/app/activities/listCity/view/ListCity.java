package com.example.weather.app.activities.listCity.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.activities.listCity.presenter.PresenterListCity;

public class ListCity extends MvpAppCompatActivity implements ViewListCity {

    @InjectPresenter PresenterListCity presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list_city);
    }
}