package com.example.weather.app.activities.main.model.onFirstStart.doCreateDB.parserFile;

import com.example.weather.data.DB.parser.ParserWeather;

import java.util.List;

import io.reactivex.Observable;

public interface iNewParserJson {

    Observable<List<ParserWeather>> returnListTempWeather();
}
