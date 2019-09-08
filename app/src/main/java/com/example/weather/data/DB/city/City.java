package com.example.weather.data.DB.city;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "city")
@Setter
@Getter
public class City {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "id_weather")
    public long idWeather;

    @ColumnInfo(name = "city_ru")
    public String cityRU;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "city_ru_to_lower")
    public String cityRUToLower;

    @ColumnInfo(name = "city_en")
    public String cityEN;

    @Builder
    public City(long idWeather, String cityRU, String country, String cityRUToLower, String cityEN) {
        this.idWeather = idWeather;
        this.cityRU = cityRU;
        this.country = country;
        this.cityRUToLower = cityRUToLower;
        this.cityEN = cityEN;
    }
}