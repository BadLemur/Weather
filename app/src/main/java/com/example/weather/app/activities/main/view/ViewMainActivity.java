package com.example.weather.app.activities.main.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ViewMainActivity extends MvpView {
    /*    PresenterMainActivityLoader*/
    @StateStrategyType(AddToEndSingleStrategy.class)
    void loadFindCity();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void doLoader();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showViewPager();

    /*    PresenterMainActivity*/
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPositionViewPager(int position);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateViewPager();
}