package com.example.weather.data.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainAppModule {
    private Context context;

    public MainAppModule(Context context) {
        this.context = context;
    }
    @Singleton
    @Provides //scope is not necessary for parameters stored within the module
    public Context context() {
        return context;
    }
}