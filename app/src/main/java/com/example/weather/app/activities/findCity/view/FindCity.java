package com.example.weather.app.activities.findCity.view;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.weather.R;

public class FindCity extends MvpAppCompatActivity implements ViewFindCity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_city);
    }

}