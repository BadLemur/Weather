package com.example.weather;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.weather.data.dagger.component.DaggerMainAppComponent;
import com.example.weather.data.dagger.component.MainAppComponent;
import com.example.weather.data.dagger.module.MainAppModule;
import com.facebook.stetho.Stetho;

public class MainApp extends Application {

    private static MainApp app;
    private MainAppComponent mainAppComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /*для просмотра бд через браузер*/
        Stetho.initializeWithDefaults(this);

        app = this;
        mainAppComponent = DaggerMainAppComponent.builder()
                .mainAppModule(new MainAppModule(getApplicationContext())).build();
    }

    public static MainApp app() {
        return app;
    }

    public MainAppComponent appComponent() {
        return mainAppComponent;
    }
}