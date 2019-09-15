package com.example.weather.data.dagger.component;

import com.example.weather.app.activities.findCity.model.ModelFindCity;
import com.example.weather.app.activities.findCity.presenter.PresenterFindCity;
import com.example.weather.app.activities.main.model.doViewModel.DoViewModel;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.OnFirstStart;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.DoCreateDB;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parserFile.NewParserJson;
import com.example.weather.app.adapter.TabsFragmentPageAdapter;
import com.example.weather.app.fragments.weatherCity.model.ModelWeatherCity;
import com.example.weather.app.fragments.weatherCity.model.ReturnTypeWeather;
import com.example.weather.data.DB.parser.ParserJson;
import com.example.weather.data.dagger.module.MainAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainAppModule.class})
public interface MainAppComponent {

    void inject(ModelFindCity app);

    void inject(ParserJson app);

    void inject(DoViewModel app);

    void inject(PresenterFindCity app);

    void inject(TabsFragmentPageAdapter app);

    void inject(ModelWeatherCity app);

    void inject(ReturnTypeWeather app);

    void inject(NewParserJson app);

    void inject(DoCreateDB app);
    void inject(OnFirstStart app);
}