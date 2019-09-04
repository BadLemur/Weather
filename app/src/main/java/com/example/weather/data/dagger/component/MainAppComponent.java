package com.example.weather.data.dagger.component;

import com.example.weather.app.activities.main.model.ModelMainActivity;
import com.example.weather.data.dagger.module.MainAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainAppModule.class})
public interface MainAppComponent {

    void inject(ModelMainActivity app);
}