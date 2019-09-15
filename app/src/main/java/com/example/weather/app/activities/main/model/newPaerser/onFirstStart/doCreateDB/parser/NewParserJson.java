package com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parser;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.weather.MainApp;
import com.example.weather.app.activities.main.model.newPaerser.onFirstStart.doCreateDB.parser.iNewParserJson;
import com.example.weather.data.DB.parser.ParserWeather;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewParserJson implements iNewParserJson {
    private static final String TAG = "NewParserJson";
    @Inject Context context;

    public NewParserJson() {
        MainApp.app().appComponent().inject(this);
    }

    @Override
    public Flowable<List<ParserWeather>> returnListTempWeather() {

        Flowable<List<ParserWeather>> flowable = Flowable.create(new FlowableOnSubscribe<List<ParserWeather>>() {
            @Override
            public void subscribe(FlowableEmitter<List<ParserWeather>> emitter) throws Exception {
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
                int index = 0;

                for (int i = 0; jsonArray.size() > i; ++i) {
                    weatherList.add(new Gson().fromJson(jsonArray.get(i), ParserWeather.class));
                    ++index;
                    if (index == 300) {
                        emitter.onNext(weatherList);
                        weatherList.clear();
                        index = 0;
                    }
                }
                emitter.onNext(weatherList);

                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io());

        return flowable;
    }
}
