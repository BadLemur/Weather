package com.example.weather.data.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weather.data.DB.city.City;
import com.example.weather.data.DB.city.CityDAO;
import com.example.weather.data.DB.cityUser.CityUser;
import com.example.weather.data.DB.cityUser.CityUserDAO;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCity;
import com.example.weather.data.DB.oldChoiceCity.OldChoiceCityDAO;

@Database(entities = {CityUser.class, City.class, OldChoiceCity.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    public static final String NAME_DB = "weather";

    public abstract CityUserDAO getCityUserDAO();

    public abstract CityDAO getCityDAO();

    public abstract OldChoiceCityDAO getOldChoiceCityDAO();
}