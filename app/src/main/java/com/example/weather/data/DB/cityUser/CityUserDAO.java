package com.example.weather.data.DB.cityUser;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CityUserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(CityUser cityUser);

    @Query("Delete from city_user where id_city =:id_server ")
    void delete(long id_server);

    @Query("Select * from city_user")
    Single<List<CityUser>> getCityUser();
}