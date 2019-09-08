package com.example.weather.app.activities.main.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ViewMainActivity extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void doLoader();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void loaderFindCity();
}