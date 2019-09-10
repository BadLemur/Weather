package com.example.weather.app.activities.main.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.activities.findCity.view.FindCity;
import com.example.weather.app.activities.main.presenter.PresenterMainActivity;
import com.example.weather.data.network.Api;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends MvpAppCompatActivity implements ViewMainActivity {

    @InjectPresenter
    PresenterMainActivity presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ib_show_city)
    ImageButton ibShowCity;
    @BindView(R.id.main_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.layout_progress_bar)
    LinearLayout layoutProgressBar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Api api = Api.Instance.getApi();
        String appid = this.getResources().getString(R.string.appid);
        String units = this.getResources().getString(R.string.units);

        api.getDataWeatherByCity("Moscow", appid, units)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataWeather -> {
//                    Toast.makeText(getApplicationContext(), dataWeather.get(0).getName() + " "
//                            + dataWeather.get(0).getMain().getTemp(), Toast.LENGTH_LONG).show();
                });

//        tabLayout.addTab(tabLayout.newTab().setText("Васька"));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_fiber_manual_record_black_24dp));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @OnClick(R.id.ib_show_city)
    public void showFindCityAct() {
        Intent intent = new Intent(getApplicationContext(), FindCity.class);
        startActivity(intent);
    }

    @Override
    public void doLoader() {
        layoutProgressBar.setVisibility(View.GONE);
        appBarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadFindCity() {
        Intent intent = new Intent(getApplicationContext(), FindCity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}