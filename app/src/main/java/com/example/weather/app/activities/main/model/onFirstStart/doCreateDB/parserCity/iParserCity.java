package com.example.weather.app.activities.main.model.onFirstStart.doCreateDB.parserCity;

import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.parser.ParserWeather;

import java.util.List;

import io.reactivex.Observable;

public interface iParserCity {
    Observable<List<City>> getListObservable(List<ParserWeather> parserWeathers);
}
