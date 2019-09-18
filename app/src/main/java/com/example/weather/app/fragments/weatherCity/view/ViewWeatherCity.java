package com.example.weather.app.fragments.weatherCity.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ViewWeatherCity extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setData(String nameCity, String temp, String typeWeather, String typeWeatherIcon);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showUpdateData();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void goneUpdateData();
}
