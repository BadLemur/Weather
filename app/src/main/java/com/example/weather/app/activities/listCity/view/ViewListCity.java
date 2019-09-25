package com.example.weather.app.activities.listCity.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.weather.app.adapter.recyclerViewListCity.AdapterListCityItem;

import java.util.List;

public interface ViewListCity extends MvpView {

    void setListAdapter(List<AdapterListCityItem> list);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void notifyDataSetChangedAdapter();
}
