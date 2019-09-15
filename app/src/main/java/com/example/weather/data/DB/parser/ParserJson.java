package com.example.weather.data.DB.parser;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.weather.MainApp;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ParserJson {
    private static final String TAG = "ParserJson";
    @Inject
    Context context;

    public ParserJson(CallbackParserData callback) {
        MainApp.app().appComponent().inject(this);
        String strJson;
        AssetManager assetManager = context.getAssets();
        try {
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

            for (int i = 0; jsonArray.size() > i; ++i)
                weatherList.add(new Gson().fromJson(jsonArray.get(i), ParserWeather.class));
            Log.e(TAG, "ParserJson: " + jsonArray.size());
            callback.returnListTempWeather(weatherList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}