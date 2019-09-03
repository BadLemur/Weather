package com.example.weather.data.DB.city;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CityDAO {
    @Insert
    void add(City city);

    @Query("Delete from city where id =:id")
    void delete(long id);
}
