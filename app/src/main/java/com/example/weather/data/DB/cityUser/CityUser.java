package com.example.weather.data.DB.cityUser;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_user",
        indices = {@Index(value = {"id_city"},
                          unique = true)})
public class CityUser {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "id_city")
    public long idWeather;


    public CityUser(long idWeather) {
        this.idWeather = idWeather;
    }
}