package com.example.weather.data.DB.oldChoiceCity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weather.data.DB.cityUser.CityUser;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface OldChoiceCityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(OldChoiceCity oldChoiceCity);

    @Query("Delete from old_choice_city where id_city =:id_server ")
    void delete(long id_server);

    @Query("Select * from old_choice_city")
    Single<List<CityUser>> getOldChoiceCity();
}
