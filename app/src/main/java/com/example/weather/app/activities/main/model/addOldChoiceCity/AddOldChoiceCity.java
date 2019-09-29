package com.example.weather.app.activities.main.model.addOldChoiceCity;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.weather.MainApp;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCity;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCityDAO;
import com.example.weather.eventBus.ClickItemCity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddOldChoiceCity implements iAddOldChoiceCity {
    private static final String TAG = "AddOldChoiceCity";
    @Inject OldChoiceCityDAO oldChoiceCityDAO;

    public AddOldChoiceCity() {
        MainApp.app().appComponent().inject(this);
        EventBus.getDefault().register(this);
    }

    private int count = 10;

    /*      Ограничиваем колличество записей count     */
    @SuppressLint("CheckResult")
    @Subscribe
    public void addWrite(ClickItemCity event) {
        Completable.fromAction(() -> {
            int count = oldChoiceCityDAO.getCount();
            if (count < this.count)
                oldChoiceCityDAO.add(new OldChoiceCity(event.getIdWeather(), event.getNameCity()));
            else {
                oldChoiceCityDAO.deleteFirstWrite();
                oldChoiceCityDAO.add(new OldChoiceCity(event.getIdWeather(), event.getNameCity()));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, throwable -> Log.e(TAG, "addWrite: ", throwable));
    }
}
