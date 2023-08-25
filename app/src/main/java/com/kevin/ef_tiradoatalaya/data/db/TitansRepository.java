package com.kevin.ef_tiradoatalaya.data.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kevin.ef_tiradoatalaya.data.model.TitanEntity;

import java.util.List;

public class TitansRepository {

    private TitanDao dao;

    private  TitanDatabase db;

    public TitansRepository(Application application) {
        db = TitanDatabase.getInstance(application);
        dao = db.titanDao();
    }

    public void addTitan(TitanEntity titanEntity){
        TitanDatabase.dataBaseWriteExecutor.execute(() ->
                dao.addTitan(titanEntity));
    }

    public LiveData<List<TitanEntity>> getAll(){
        return dao.getAll();
    }
}
