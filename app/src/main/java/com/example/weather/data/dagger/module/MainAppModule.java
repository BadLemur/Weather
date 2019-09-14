package com.example.weather.data.dagger.module;

import android.content.Context;

import com.example.weather.data.DB.CreateDB;
import com.example.weather.data.DB.RoomDB;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.cityUser.CityUserDAO;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCityDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainAppModule {
    private Context context;

    public MainAppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context context() {
        return context;
    }

    @Singleton
    @Provides
    public RoomDB provideRoomDB(Context context) {
        return new CreateDB(context).getRoomDB();
    }

    @Singleton
    @Provides
    public CityUserDAO provideCustomMenuDAO(RoomDB roomDB) {
        return roomDB.getCityUserDAO();
    }

    @Singleton
    @Provides
    public CityDAO provideCityDAO(RoomDB roomDB) {
        return roomDB.getCityDAO();
    }

    @Singleton
    @Provides
    public OldChoiceCityDAO provideOldChoiceCityDAO(RoomDB roomDB) {
        return roomDB.getOldChoiceCityDAO();
    }
}