package com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parserFile;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.weather.MainApp;
import com.example.weather.data.DB.parser.ParserWeather;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class NewParserJson implements iNewParserJson {

    @Inject Context context;

    public NewParserJson() {
        MainApp.app().appComponent().inject(this);
    }

    @Override
    public Observable<List<ParserWeather>> returnListTempWeather() {

        Observable<List<ParserWeather>> observable = Observable.create(new ObservableOnSubscribe<List<ParserWeather>>() {

            @Override
            public void subscribe(ObservableEmitter<List<ParserWeather>> emitter) throws Exception {
                String strJson;
                AssetManager assetManager = context.getAssets();
                InputStream stream = assetManager.open("city.json");
                int size = stream.available();
                byte[] buffer = new byte[size];
                stream.read(buffer);
                stream.close();

                strJson = new String(buffer, "UTF-8");
                JsonParser parser = new JsonParser();
                Object obj = parser.parse(strJson);
                JsonArray jsonArray = (JsonArray) obj;
                List<ParserWeather> weatherList = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; jsonArray.size() > i; ++i) {
                    weatherList.add(gson.fromJson(jsonArray.get(i), ParserWeather.class));
                }
                emitter.onNext(weatherList);
            }
        });
        return observable;
    }
}