package com.kevin.ef_tiradoatalaya.data.retrofit;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kevin.ef_tiradoatalaya.data.model.Titan;
import com.kevin.ef_tiradoatalaya.databinding.ItemTitanBinding;

import java.util.List;

public class RVTitanAdapter extends RecyclerView.Adapter<RVTitanAdapter.ShowViewHolder> {

    private List<Titan> titans;

    public RVTitanAdapter(List<Titan> titans){
        this.titans = titans;
    }


    @NonNull
    @Override
    public RVTitanAdapter.ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTitanBinding binding = ItemTitanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ShowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RVTitanAdapter.ShowViewHolder holder, int position) {
        holder.bind(titans.get(position));
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

        public void bind(Titan titan) {
            binding.txtName.setText(titan.getName());
            String abilitiesText = TextUtils.join(", ", titan.getAbilities()); // Concatena las habilidades con una coma y espacio
            binding.txtAbilities.setText(abilitiesText);
            Glide.with(itemView.getContext()).load(titan.getImg()).into(binding.imgView);
        }
    }
}
