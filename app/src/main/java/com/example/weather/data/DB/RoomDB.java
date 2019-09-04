package com.example.weather.data.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.data.DB.cityUser.CityUserDAO;

@Database(entities = {CityUser.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    public static final String NAME_DB = "weather";

    public abstract CityUserDAO getCityUserDAO();
}