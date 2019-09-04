package com.example.weather.app.activities.main.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.activities.findCity.FindCity;
import com.example.weather.app.activities.main.presenter.PresenterMainActivity;
import com.example.weather.data.network.Api;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends MvpAppCompatActivity implements ViewMainActivity {
    private static final String TAG = "MainActivity";
    @InjectPresenter
    PresenterMainActivity presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.show_city)
    ImageButton ib_showCity;
    @BindView(R.id.main_tab_layout)
    TabLayout tabLayout;

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
        Log.d(TAG, "onCreate: " + appid);
        Log.d(TAG, "onCreate: " + units);
        api.getDataWeatherByCity("Moscow", appid, units)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataWeather -> {
                    Toast.makeText(getApplicationContext(), dataWeather.getName() + " " + dataWeather.getMain().getTemp(), Toast.LENGTH_LONG).show();
                });

//        tabLayout.addTab(tabLayout.newTab().setText("Васька"));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_fiber_manual_record_black_24dp));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public void loaderCity() {
        Log.d(TAG, "loaderFindCity: loaderCity");
    }

    @Override
    public void loaderFindCity() {
        Log.d(TAG, "loaderFindCity: loaderFindCity");
        Intent intent = new Intent(getApplicationContext(), FindCity.class);
        startActivity(intent);
    }
}