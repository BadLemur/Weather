package com.example.weather.data.temp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.weather.MainApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TempParserJson {
    private static final String TAG = "TempParserJson";
    @Inject
    Context context;

    ArrayList<String> strings = new ArrayList();

    public TempParserJson() {
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
            Log.d(TAG, "TempParserJson: " + jsonArray.get(0));

            TempWeather tempWeather = new Gson().fromJson(jsonArray.get(0), TempWeather.class);
            Log.d(TAG, "TempParserJson: " + tempWeather.getId());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}