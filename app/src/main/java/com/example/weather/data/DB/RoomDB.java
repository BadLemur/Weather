package com.example.weather.data.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weather.data.DB.cityUserList.CityUserList;
import com.example.weather.data.DB.cityUserList.CityUserListDAO;

@Database(entities = {CityUserList.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    public static final String NAME_DB = "weather";

    public abstract CityUserListDAO getCityUserListDAO();
}