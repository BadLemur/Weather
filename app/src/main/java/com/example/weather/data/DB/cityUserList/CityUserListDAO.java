package com.example.weather.data.DB.cityUserList;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CityUserListDAO {

    @Insert
    void add(CityUserList cityUserList);

    @Query("Delete from city_user_list where id_server =:id_server ")
    void delete(long id_server);
}
