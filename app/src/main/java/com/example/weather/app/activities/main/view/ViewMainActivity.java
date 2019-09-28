package com.example.weather.app.activities.main.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.weather.customStrategy.CustomStrategy;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ViewMainActivity extends MvpView {

    @StateStrategyType(value = CustomStrategy.class, tag = "test")
    void showFindCity();

    @StateStrategyType(value = CustomStrategy.class, tag = "test")
    void showListCity();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void doLoader();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPositionViewPager(int position);

    @StateStrategyType(value = CustomStrategy.class, tag = "test")
    void updateViewPager();
}