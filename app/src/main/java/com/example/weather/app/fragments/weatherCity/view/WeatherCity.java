package com.example.weather.app.fragments.weatherCity.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.weather.R;
import com.example.weather.app.fragments.weatherCity.presenter.PresenterWeatherCity;

import javax.annotation.Resources;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WeatherCity extends MvpAppCompatFragment implements ViewWeatherCity {
    private static final String TAG = "WeatherCity";
    private Typeface typeface;

    @InjectPresenter
    PresenterWeatherCity presenter;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.tv_connection_to_server) TextView connectionServer;
    @BindView(R.id.tv_name_city) TextView nameCity;
    @BindView(R.id.tv_temp) TextView temp;
    @BindView(R.id.tv_weather) TextView weather;

    @BindView(R.id.tv_image_weather_city) TextView imageWeather;
    private View root;
    private Unbinder unbinder;

    int height = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.weather_city, container, false);
        unbinder = ButterKnife.bind(this, root);

        Bundle bundle = getArguments();
        presenter.setIdWeather(bundle.getLong("idWeather"));

        typeface = ResourcesCompat.getFont(getContext(), R.font.font);
        imageWeather.setTypeface(typeface);


        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;

        root.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });
        return root;
    }

    @Override
    public void setData(String nameCity, String temp, String typeWeather, String typeWeatherIcon) {
        this.nameCity.setText(nameCity);
        this.temp.setText(temp);
        weather.setText(typeWeather);
        imageWeather.setText(typeWeatherIcon);
    }

    GestureDetector.SimpleOnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    presenter.setMotionSize(height, (int) e1.getY(), (int) e2.getY());
                    return true;
                }
            };

    GestureDetector gestureDetector = new GestureDetector(getContext(),
            onGestureListener);

    @Override
    public void showUpdateData() {
        connectionServer.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneUpdateData() {
        connectionServer.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}