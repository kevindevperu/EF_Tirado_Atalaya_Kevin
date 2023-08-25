package com.kevin.ef_tiradoatalaya.data.fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kevin.ef_tiradoatalaya.data.db.TitanDao;
import com.kevin.ef_tiradoatalaya.data.db.TitanDatabase;
import com.kevin.ef_tiradoatalaya.data.db.TitansRepository;
import com.kevin.ef_tiradoatalaya.data.model.Titan;
import com.kevin.ef_tiradoatalaya.data.model.TitanEntity;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private TitansRepository repository;
    public LiveData<List<TitanEntity>> listLiveData = new MutableLiveData<List<TitanEntity>>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new TitansRepository(application);
    }

    public void addTitan(Titan titan){
        TitanEntity titanEntity = new TitanEntity();
        titanEntity.setName(titan.getName());
        titanEntity.setImg(titan.getImg());
        titanEntity.setHeight(titan.getHeight());
        titanEntity.setAllegiance(titan.getAllegiance());
        repository.addTitan(titanEntity);
    }

    public void getTitans(){
        listLiveData = repository.getAll();
    }

    public class HomeViewModelFactory implements ViewModelProvider.Factory{
        private final Application application;
        public HomeViewModelFactory(Application myApplication){
            application = myApplication;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new HomeViewModel(application);
        }
    }
}
