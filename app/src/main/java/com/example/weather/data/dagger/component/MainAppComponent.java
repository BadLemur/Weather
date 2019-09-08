package com.example.weather.data.dagger.component;

import com.example.weather.app.activities.findCity.model.ModelFindCity;
import com.example.weather.app.activities.main.model.doCreateDB.ModelLoader;
import com.example.weather.app.activities.main.model.doCreateDB.OnCreateFirstDB;
import com.example.weather.app.activities.main.model.doViewModel.DoViewModel;
import com.example.weather.data.DB.parser.ParserJson;
import com.example.weather.data.dagger.module.MainAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainAppModule.class})
public interface MainAppComponent {

    void inject(ModelLoader app);

    void inject(OnCreateFirstDB app);

    void inject(ModelFindCity app);

    void inject(ParserJson app);

    void inject(DoViewModel app);
}