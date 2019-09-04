package com.example.weather.data.dagger.module;

import android.content.Context;

import androidx.room.Room;

import com.example.weather.data.DB.RoomDB;
import com.example.weather.data.DB.cityUser.CityUserDAO;

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
    @Provides //scope is not necessary for parameters stored within the module
    public Context context() {
        return context;
    }

    @Singleton
    @Provides
    public RoomDB provideRoomDB(Context context) {
        return Room.databaseBuilder(context, RoomDB.class, RoomDB.NAME_DB).build();
    }

    @Singleton
    @Provides
    public CityUserDAO provideCustomMenuDAO(RoomDB roomDB) {
        return roomDB.getCityUserDAO();
    }
}