package com.kevin.ef_tiradoatalaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;

import com.kevin.ef_tiradoatalaya.data.fragments.HomeFragment;
import com.kevin.ef_tiradoatalaya.databinding.ActivityDetalleBinding;

public class DetalleActivity extends AppCompatActivity {

    private ActivityDetalleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.tb_detalle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detalles del Titan");
        addHomeFragment2();
    }

    private void addHomeFragment2(){
        getSupportFragmentManager().
                beginTransaction()
                .add(binding.listTitanDetalle.getId(), new HomeFragment()).commit();
    }

}