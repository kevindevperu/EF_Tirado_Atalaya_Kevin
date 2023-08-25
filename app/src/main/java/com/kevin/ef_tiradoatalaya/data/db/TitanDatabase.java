package com.kevin.ef_tiradoatalaya.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kevin.ef_tiradoatalaya.data.model.TitanEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TitanEntity.class}, version = 1)
public abstract class TitanDatabase extends RoomDatabase {

    public abstract TitanDao titanDao();

    private static volatile TitanDatabase db;

    public static final ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static TitanDatabase getInstance(Context context){
        if(db == null){
            synchronized (TitanDatabase.class){
                if(db == null){
                    db = Room.databaseBuilder(context.getApplicationContext(),TitanDatabase.class,"titans_database").build();
                }
            }
        }
        return db;
    }
}
