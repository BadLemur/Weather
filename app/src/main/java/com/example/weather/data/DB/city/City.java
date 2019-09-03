package com.example.weather.data.DB.city;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Builder;

@Entity(tableName = "city")
public class City {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "city_ru")
    public String cityRU;

    @ColumnInfo(name = "city_ru_to_lower")
    public String cityRUToLower;

    @ColumnInfo(name = "city_en")
    public String cityEN;

    @Builder
    public City(String cityRU, String cityRUToLower, String cityEN) {
        this.cityRU = cityRU;
        this.cityRUToLower = cityRUToLower;
        this.cityEN = cityEN;
    }
}