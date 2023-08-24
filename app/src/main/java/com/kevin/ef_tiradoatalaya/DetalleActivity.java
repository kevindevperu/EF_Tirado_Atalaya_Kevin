package com.kevin.ef_tiradoatalaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.kevin.ef_tiradoatalaya.data.fragments.DetalleFragment;
import com.kevin.ef_tiradoatalaya.data.fragments.HomeFragment;
import com.kevin.ef_tiradoatalaya.data.model.Titan;
import com.kevin.ef_tiradoatalaya.data.retrofit.RetrofitHelper;
import com.kevin.ef_tiradoatalaya.data.retrofit.TitanResponse;
import com.kevin.ef_tiradoatalaya.data.retrofit.TitansInterface;
import com.kevin.ef_tiradoatalaya.databinding.ActivityDetalleBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleActivity extends AppCompatActivity {

    private ActivityDetalleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Infla la vista de la actividad desde activity_detalle.xml
        binding = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = findViewById(R.id.tb_detalle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detalles del Titan");

        // Obtén el ID del titán de la intención que abre la actividad
        Intent intent = getIntent();
        int titanId = intent.getIntExtra("titan_id", -1); // -1 es un valor predeterminado si no se encuentra el extra
        // Ahora, realiza una solicitud a la API para obtener detalles del titán por su ID
        TitansInterface titansInterface = RetrofitHelper.getInstance().create(TitansInterface.class);
        Call<Titan> titanCall = titansInterface.getTitanById(titanId);

        titanCall.enqueue(new Callback<Titan>() {
            @Override
            public void onResponse(Call<Titan> call, Response<Titan> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Titan selectedTitan = response.body();

                    // Llama al método en el fragmento para mostrar los detalles
                    HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.listTitanDetalle);
                    if (homeFragment != null) {
                        homeFragment.displayTitanDetails(selectedTitan);
                    }
                    Log.d("Debug", "Llamando a displayTitanDetails");

                    // Luego, crea el DetalleFragment y realiza la transacción del fragmento
                    DetalleFragment detalleFragment = new DetalleFragment();
                    detalleFragment.setTitan(selectedTitan); // Establece el titán que se mostrará en el fragmento
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.listTitanDetalle, detalleFragment) // Reemplaza el fragmento existente
                            .commit();
                } else {
                    Log.e("API Response Error", "Response code: " + response.code());
                    // Manejar la respuesta de error de la API
                }
            }

            @Override
            public void onFailure(Call<Titan> call, Throwable t) {
                // Manejar el fallo de la llamada a la API
                Log.e("API Error", "Error en la solicitud a la API: " + t.getMessage());
            }
        });
    }
}