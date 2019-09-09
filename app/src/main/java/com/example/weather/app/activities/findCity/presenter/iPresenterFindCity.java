package com.example.weather.app.activities.findCity.presenter;

import io.reactivex.Observable;

public interface iPresenterFindCity {
    void searchViewObservable(Observable<String> observable);
}
