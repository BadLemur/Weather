package com.example.weather.data.dagger.component;

import com.example.weather.app.activities.findCity.model.ModelFindCity;
import com.example.weather.app.activities.main.model.ModelMainActivity;
import com.example.weather.app.activities.main.model.OnCreateFirstDB;
import com.example.weather.data.DB.parser.ParserJson;
import com.example.weather.data.dagger.module.MainAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainAppModule.class})
public interface MainAppComponent {

    void inject(ModelMainActivity app);

    void inject(OnCreateFirstDB app);

    void inject(ModelFindCity app);

    void inject(ParserJson app);
}