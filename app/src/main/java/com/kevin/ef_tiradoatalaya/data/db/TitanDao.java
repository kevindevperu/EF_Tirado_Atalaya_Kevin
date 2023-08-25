package com.kevin.ef_tiradoatalaya.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kevin.ef_tiradoatalaya.data.model.TitanEntity;

import java.util.List;

@Dao
public interface TitanDao {
    @Insert
    public void addTitan(TitanEntity titan);

    @Query("SELECT * FROM titan WHERE titan_name LIKE :name LIMIT 1")
    public TitanEntity getTitanByName(String name);

    @Query("SELECT * FROM titan")
    public LiveData<List<TitanEntity>> getAll();

    @Delete
    public void deleteTitan(TitanEntity titan);
}
