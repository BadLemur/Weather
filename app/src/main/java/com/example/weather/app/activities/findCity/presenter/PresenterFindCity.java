package com.example.weather.app.activities.findCity.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.weather.MainApp;
import com.example.weather.app.activities.findCity.model.ModelFindCity;
import com.example.weather.app.activities.findCity.model.iModelFindCity;
import com.example.weather.app.activities.findCity.view.ViewFindCity;
import com.example.weather.app.adapter.ItemAdapterFindCity;
import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PresenterFindCity extends MvpPresenter<ViewFindCity> implements iPresenterFindCity {
    private static final String TAG = "PresenterFindCity";
    @Inject
    CityDAO cityDAO;
    private iModelFindCity model;

    public PresenterFindCity() {
        MainApp.app().appComponent().inject(this);
        model = new ModelFindCity(this);
    }

    private Disposable disposable;
    private List<ItemAdapterFindCity> findCityList = new ArrayList<>();

    @SuppressLint("CheckResult")
    @Override
    public void searchViewObservable(Observable<String> observable) {
        observable
                .subscribeOn(Schedulers.io())
                .map(s -> s.trim().toLowerCase().trim())
                .debounce(250, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .flatMap((Function<String, Observable<List<City>>>)
                        s -> cityDAO.getFindToLike("%" + s + "%"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCity -> {
                    findCityList.clear();
                    for (City city : listCity) {
                        ItemAdapterFindCity item = ItemAdapterFindCity
                                .builder()
                                .idWeather(city.idWeather)
                                .country(city.country)
                                .build();
                        if (city.getCityEN() != null)
                            item.setNameCityEN(city.getCityEN());
                        if (city.getCityRU() != null)
                            item.setNameCityRU(city.getCityRU());
                        findCityList.add(item);
                    }
                    getViewState().updateRecyclerView(findCityList);
                }, throwable -> {
                    Log.e(TAG, "searchViewObservable: " + throwable);
                }, () -> {
                }, disposable -> {
                    this.disposable = disposable;
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}