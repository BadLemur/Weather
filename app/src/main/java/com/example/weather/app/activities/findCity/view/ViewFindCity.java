package com.example.weather.app.activities.findCity.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.weather.app.adapter.recyclerViewFindCity.ItemAdapterFindCity;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCity;

import java.util.List;

public interface ViewFindCity extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setList(List<ItemAdapterFindCity> findCityList);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setVisible();

    @StateStrategyType(SkipStrategy.class)
    void updateRecyclerView();

    @StateStrategyType(SkipStrategy.class)
    void createListOldCity(OldChoiceCity listOldCity);
}