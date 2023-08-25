package com.kevin.ef_tiradoatalaya.data.retrofit;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.kevin.ef_tiradoatalaya.data.model.Titan;
import com.kevin.ef_tiradoatalaya.databinding.ItemDetalleTitanBinding;
import java.util.List;

public class RVDetalleTitanAdapter extends RecyclerView.Adapter<RVDetalleTitanAdapter.DetalleTitanViewHolder> {

    private List<Titan> titans;
    private Context context;


    private FavoriteClick favoriteClick;


    public RVDetalleTitanAdapter(List<Titan> titans, FavoriteClick favoriteClick) {
        this.titans = titans;
        this.favoriteClick = favoriteClick;

    }

    public void setTitan(Titan titan) {
        titans.clear(); // Limpia la lista existente (si la hay)
        titans.add(titan); // Agrega el titán proporcionado a la lista
        notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
    }

    @NonNull
    @Override
    public DetalleTitanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetalleTitanBinding binding = ItemDetalleTitanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DetalleTitanViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleTitanViewHolder holder, int position) {
        Titan titan = titans.get(position);
        holder.bind(titan);
    }

    @Override
    public int getItemCount() {
        return titans.size();
    }

    public class DetalleTitanViewHolder extends RecyclerView.ViewHolder {
        private ItemDetalleTitanBinding binding;

        public DetalleTitanViewHolder(ItemDetalleTitanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Titan titan) {
            binding.txtNames.setText("Nombre: " + titan.getName());
            binding.txtHeight.setText("Altura: " + titan.getHeight());
            binding.txtAllegiance.setText("Lealtad: " + titan.getAllegiance());

            // Si `abilities` es una lista, puedes formatearla como desees, aquí se usa TextUtils para unirla con comas
            String abilitiesText = TextUtils.join(", ", titan.getAbilities());
            binding.txtAbilitiess.setText("Habilidades: " + abilitiesText);

            // Carga la imagen del titán utilizando Glide o cualquier otra biblioteca de carga de imágenes
            Glide.with(context).load(titan.getImg()).into(binding.imgTitan);
        }
    }
    public interface FavoriteClick{
        public void onClick(Titan titan);
    }

}