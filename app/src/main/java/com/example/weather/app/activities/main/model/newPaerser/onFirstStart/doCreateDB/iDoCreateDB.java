package com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB;

import com.example.weather.data.DB.parser.ParserWeather;

import java.util.List;

import io.reactivex.Flowable;

public interface iDoCreateDB {

    void createDB();

    void setFlowable(Flowable<List<ParserWeather>> flowable);
}