package com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parserCity;

import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.parser.ParserWeather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class ParserCity implements iParserCity {
    @Override
    public Observable<List<City>> getListObservable(List<ParserWeather> parserWeathers) {

        Observable<List<City>> observable = Observable.create(emitter -> {
            List<City> cityList = new ArrayList<>();
            for (int index = 0; parserWeathers.size() > index; ++index) {
                ParserWeather weather = parserWeathers.get(index);
                City city = City.builder()
                        .idWeather(weather.getId())
                        .country(weather.getCountry())
                        .cityEN(weather.getNameCity())
                        .build();
                cityList.add(city);
            }
            emitter.onNext(cityList);
        });
        return observable;
    }
}