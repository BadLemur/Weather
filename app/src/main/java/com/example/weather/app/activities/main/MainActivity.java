package com.example.weather.app.activities.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.weather.R;
import com.example.weather.data.network.Api;
import com.example.weather.data.network.data.DataWeather;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class MainActivity extends MvpAppCompatActivity implements View_MainActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.show_city)
    ImageButton ib_showCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Api api = Api.Instance.getApi();

        api.getDataWeatherByCity("Moscow", R.string.appid, R.string.units);


        Log.d(TAG, "onCreate: ");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public void loader() {

    }
}