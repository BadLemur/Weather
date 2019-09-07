package com.example.weather.data.DB;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.weather.MainApp;

public class CreateDB {

    private Context context;

    public CreateDB(Context context) {
        this.context = context;
    }

    public RoomDB getRoomDB() {
        RoomDatabase.Builder<RoomDB> roomDBBuilder = Room
                .databaseBuilder(context, RoomDB.class, RoomDB.NAME_DB);
        return roomDBBuilder.build();
    }
}