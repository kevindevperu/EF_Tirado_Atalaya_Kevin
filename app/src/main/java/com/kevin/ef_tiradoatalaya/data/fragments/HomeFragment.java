package com.kevin.ef_tiradoatalaya.data.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kevin.ef_tiradoatalaya.DetalleActivity;
import com.kevin.ef_tiradoatalaya.R;
import com.kevin.ef_tiradoatalaya.data.model.Titan;
import com.kevin.ef_tiradoatalaya.data.retrofit.RVTitanAdapter;
import com.kevin.ef_tiradoatalaya.data.retrofit.RetrofitHelper;
import com.kevin.ef_tiradoatalaya.data.retrofit.TitanResponse;
import com.kevin.ef_tiradoatalaya.data.retrofit.TitansInterface;
import com.kevin.ef_tiradoatalaya.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private RVTitanAdapter rvTitanAdapter;
    private List<Titan> titansList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.rvTitans;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        TitansInterface titansInterface = RetrofitHelper.getInstance().create(TitansInterface.class);

        Call<TitanResponse> call = titansInterface.getListTitan();
        call.enqueue(new Callback<TitanResponse>() {
            @Override
            public void onResponse(Call<TitanResponse> call, Response<TitanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TitanResponse titansResponse = response.body();
                    titansList = titansResponse.getResults(); // Actualiza la lista en lugar de declarar una nueva

                    // Ahora puedes trabajar con la lista de titanes
                    Log.d("API Response", "Titan count: " + titansList.size());

                    // Configura el RecyclerView con los datos
                    rvTitanAdapter = new RVTitanAdapter(titansList, onItemClickListener);
                    recyclerView.setAdapter(rvTitanAdapter);
                } else {
                    Log.e("API Response Error", "Response code: " + response.code());
                    // Manejar la respuesta de error de la API
                }
            }

            @Override
            public void onFailure(Call<TitanResponse> call, Throwable t) {
                // Manejar el fallo de la llamada a la API
                Log.e("API Error", "Error en la solicitud a la API: " + t.getMessage());
            }
        });
    }

    // Mueve este método fuera del onItemClickListener
    public void displayTitanDetails(Titan titan) {
        if (binding != null) {
            View titanDetailView = LayoutInflater.from(requireContext()).inflate(R.layout.item_detalle_titan, null);
            TextView nameTextView = titanDetailView.findViewById(R.id.txtNames);
            TextView heightTextView = titanDetailView.findViewById(R.id.txtHeight);
            TextView allegianceTextView = titanDetailView.findViewById(R.id.txtAllegiance);
            TextView abilitiesTextView = titanDetailView.findViewById(R.id.txtAbilitiess);
            ImageView imgTitanView = titanDetailView.findViewById(R.id.imgTitan);

            // Ahora, configura las vistas con los detalles del titán
            nameTextView.setText("Nombre: " + titan.getName());
            heightTextView.setText("Altura: " + titan.getHeight());
            allegianceTextView.setText("Lealtad: " + titan.getAllegiance());

            // Si `abilities` es una lista, puedes formatearla como desees, aquí se usa TextUtils para unirla con comas
            String abilitiesText = TextUtils.join(", ", titan.getAbilities());
            abilitiesTextView.setText("Habilidad: " + abilitiesText);

            // Glide u otra biblioteca para cargar imágenes
            Glide.with(requireContext()).load(titan.getImg()).into(imgTitanView);

        }
    }

    private RVTitanAdapter.OnItemClickListener onItemClickListener = new RVTitanAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            // Verifica si la lista titansList no está vacía y si la posición es válida
            if (!titansList.isEmpty() && position >= 0 && position < titansList.size()) {
                // Obtén el ID del Titán seleccionado
                int titanId = titansList.get(position).getId();

                // Crea un Intent y pasa el ID del Titán a la actividad DetalleActivity
                Intent intent = new Intent(getActivity(), DetalleActivity.class);
                intent.putExtra("titan_id", titanId);
                startActivity(intent);
            } else {
                // Manejar el caso en el que no hay elementos en la lista o la posición es inválida
                Log.e("ItemClick Error", "Lista de titanes vacía o posición inválida.");
            }
        }
    };
}