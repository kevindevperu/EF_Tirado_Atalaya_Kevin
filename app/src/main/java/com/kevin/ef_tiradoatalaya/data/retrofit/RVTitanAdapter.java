package com.kevin.ef_tiradoatalaya.data.retrofit;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kevin.ef_tiradoatalaya.data.model.Titan;
import com.kevin.ef_tiradoatalaya.databinding.ItemTitanBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RVTitanAdapter extends RecyclerView.Adapter<RVTitanAdapter.ShowViewHolder> {


    private List<Titan> titans;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }



    public RVTitanAdapter(List<Titan> titans, OnItemClickListener listener) {
        this.titans = titans;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RVTitanAdapter.ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTitanBinding binding = ItemTitanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ShowViewHolder(binding);
    }
    

    @Override
    public void onBindViewHolder(@NonNull RVTitanAdapter.ShowViewHolder holder, int position) {
        holder.bind(titans.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return titans.size();
    }

    public class ShowViewHolder extends RecyclerView.ViewHolder {
        private ItemTitanBinding binding;

        public ShowViewHolder(@NonNull ItemTitanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Titan titan, OnItemClickListener listener) {
            binding.txtName.setText(titan.getName());
            String abilitiesText = TextUtils.join(", ", titan.getAbilities());
            binding.txtAbilities.setText(abilitiesText);

            Glide.with(itemView.getContext()).load(titan.getImg()).into(binding.imgView);


            // Configura el OnClickListener en la tarjeta
            binding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);


                            // Obtiene el ID del titán seleccionado
                            int titanId = titans.get(position).getId();

                            // Realiza una solicitud adicional para obtener detalles del titán por su ID
                            TitansInterface titansInterface = RetrofitHelper.getInstance().create(TitansInterface.class);
                            Call<Titan> titanCall = titansInterface.getTitanById(titanId);
                            titanCall.enqueue(new Callback<Titan>() {
                                @Override
                                public void onResponse(Call<Titan> call, Response<Titan> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        Titan selectedTitan = response.body();
                                        // Aquí puedes trabajar con el titán seleccionado y sus detalles
                                        Log.d("Selected Titan", "ID: " + selectedTitan.getId());
                                        Log.d("Selected Titan", "Name: " + selectedTitan.getName());
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
                }
            });
        }
    }

}
