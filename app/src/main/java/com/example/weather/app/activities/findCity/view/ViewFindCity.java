package com.example.weather.app.activities.findCity.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.weather.app.adapter.ItemAdapterFindCity;

import java.util.List;

public interface ViewFindCity extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void updateRecyclerView(List<ItemAdapterFindCity> findCityList);
}
