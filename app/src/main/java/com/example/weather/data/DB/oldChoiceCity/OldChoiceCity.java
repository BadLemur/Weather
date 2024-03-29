package com.example.weather.data.DB.oldChoiceCity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "old_choice_city",
        indices = {@Index(value = {"id_city"},
                          unique = true)})
public class OldChoiceCity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "id_city")
    public long idWeather;

    @ColumnInfo(name = "name_city")
    public String nameCity;

    public OldChoiceCity(long idWeather, String nameCity) {
        this.idWeather = idWeather;
        this.nameCity = nameCity;
    }
}