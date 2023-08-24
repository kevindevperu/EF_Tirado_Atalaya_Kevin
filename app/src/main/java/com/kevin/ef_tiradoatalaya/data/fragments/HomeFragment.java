package com.kevin.ef_tiradoatalaya.data.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kevin.ef_tiradoatalaya.DetalleActivity;
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

    private RVTitanAdapter.OnItemClickListener onItemClickListener = new RVTitanAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            // Aquí puedes abrir la actividad de detalles
            Intent intent = new Intent(getActivity(), DetalleActivity.class);
            startActivity(intent);
        }
    };

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

        Call <TitanResponse> call = titansInterface.getListTitan();
        call.enqueue(new Callback<TitanResponse>() {
            @Override
            public void onResponse(Call<TitanResponse> call, Response<TitanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TitanResponse titansResponse = response.body();
                    List<Titan> titansList = titansResponse.getResults();

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
}