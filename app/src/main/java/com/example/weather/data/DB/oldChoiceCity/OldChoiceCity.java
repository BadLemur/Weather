package com.example.weather.data.DB.oldChoiceCity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "old_choice_city")
public class OldChoiceCity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "id_city")
    public long idWeather;

    public OldChoiceCity(long idWeather) {
        this.idWeather = idWeather;
    }
}