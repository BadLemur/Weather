package com.example.weather.app.activities.listCity.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.activities.findCity.view.FindCity;
import com.example.weather.app.activities.listCity.presenter.PresenterListCity;
import com.example.weather.app.adapter.recyclerViewListCity.AdapterListCity;
import com.example.weather.app.adapter.recyclerViewListCity.AdapterListCityItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListCity extends MvpAppCompatActivity implements ViewListCity {

    @InjectPresenter PresenterListCity presenter;

    @BindView(R.id.container_RecyclerView) RecyclerView recyclerView;
    @BindView(R.id.FloatingActionButton) FloatingActionButton floatingActionButton;
    private AdapterListCity adapterListCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list_city);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        adapterListCity = new AdapterListCity();
        recyclerView.setAdapter(adapterListCity);
        floatingActionButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.floating_scale));
    }

    @OnClick(R.id.FloatingActionButton)
    public void floatingActionButton(View v) {
        Intent intent = new Intent(getApplicationContext(), FindCity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void setListAdapter(List<AdapterListCityItem> list) {
        adapterListCity.setList(list);
        adapterListCity.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChangedAdapter() {
        adapterListCity.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}