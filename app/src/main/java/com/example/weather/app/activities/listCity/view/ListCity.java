package com.example.weather.app.activities.listCity.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.activities.findCity.view.FindCity;
import com.example.weather.app.activities.listCity.presenter.PresenterListCity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListCity extends MvpAppCompatActivity implements ViewListCity {

    @InjectPresenter PresenterListCity presenter;

    @BindView(R.id.container_RecyclerView) RecyclerView containerRecyclerView;
    @BindView(R.id.FloatingActionButton) FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list_city);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @OnClick(R.id.FloatingActionButton)
    public void floatingActionButton(View v) {
        Intent intent = new Intent(getApplicationContext(), FindCity.class);
        startActivity(intent);
        this.finish();
    }
}