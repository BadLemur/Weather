package com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parser;

import com.example.weather.data.DB.parser.ParserWeather;

import java.util.List;

import io.reactivex.Flowable;

public interface iNewParserJson {

    Flowable<List<ParserWeather>> returnListTempWeather();
}
