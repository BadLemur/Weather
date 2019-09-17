package com.example.weather.app.activities.main.model.addOldChoiceCity;

import com.example.weather.MainApp;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCity;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCityDAO;
import com.example.weather.eventBus.ClickItemRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddOldChoiceCity implements iAddOldChoiceCity {

    @Inject OldChoiceCityDAO oldChoiceCityDAO;

    public AddOldChoiceCity() {
        MainApp.app().appComponent().inject(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void addWrite(ClickItemRecyclerView event) {
        Completable.fromAction(() -> {
            oldChoiceCityDAO.add(new OldChoiceCity(event.getIdWeather()));
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
