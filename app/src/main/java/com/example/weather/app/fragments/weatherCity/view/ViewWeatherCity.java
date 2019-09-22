package com.example.weather.app.fragments.weatherCity.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.weather.app.fragments.weatherCity.wrapper.WrapperData;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ViewWeatherCity extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setData(WrapperData wrapperData);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showUpdateData();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void goneUpdateData();
}
