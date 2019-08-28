package com.example.weather.app.activities.main;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.weather.R;

public class MainActivity extends MvpAppCompatActivity implements View_MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_activity);
    }
}
