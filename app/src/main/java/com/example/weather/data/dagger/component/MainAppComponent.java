package com.example.weather.data.dagger.component;

import com.example.weather.app.activities.findCity.model.ModelFindCity;
import com.example.weather.app.activities.findCity.presenter.PresenterFindCity;
import com.example.weather.app.activities.main.model.doCreateDB.ModelLoader;
import com.example.weather.app.activities.main.model.doCreateDB.CreateFirstDB;
import com.example.weather.app.activities.main.model.doViewModel.DoViewModel;
import com.example.weather.data.DB.parser.ParserJson;
import com.example.weather.data.dagger.module.MainAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainAppModule.class})
public interface MainAppComponent {

    void inject(ModelLoader app);

    void inject(CreateFirstDB app);

    void inject(ModelFindCity app);

    void inject(ParserJson app);

    void inject(DoViewModel app);

    void inject(PresenterFindCity app);
}