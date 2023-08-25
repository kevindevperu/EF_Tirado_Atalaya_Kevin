package com.kevin.ef_tiradoatalaya.data.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.kevin.ef_tiradoatalaya.R;
import com.kevin.ef_tiradoatalaya.data.model.Titan;
import com.kevin.ef_tiradoatalaya.data.retrofit.RVDetalleTitanAdapter;
import com.kevin.ef_tiradoatalaya.databinding.FragmentDetalleBinding;

import java.util.List;

public class DetalleFragment extends Fragment {

    private FragmentDetalleBinding binding;

    private Titan titan;

    public DetalleFragment() {

    }
    private HomeViewModel homeViewModel;

    // Este método se llama para configurar el titán cuyos detalles se mostrarán en el fragmento
    public void setTitan(Titan titan) {
        this.titan = titan;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detalle_titan, container, false);
        Button favoriteButton = rootView.findViewById(R.id.btn_favorite);
        // Configura un OnClickListener para el botón de favorito
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titan != null) {
                    // Agregar el Titan actual a la base de datos utilizando el ViewModel
                    homeViewModel.addTitan(titan);
                    // Muestra un mensaje de confirmación
                    Toast.makeText(requireContext(), "Titan agregado a favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Encuentra las vistas en el diseño del fragmento
        ImageView imgTitanView = rootView.findViewById(R.id.imgTitan);
        TextView nameTextView = rootView.findViewById(R.id.txtNames);
        TextView heightTextView = rootView.findViewById(R.id.txtHeight);
        TextView allegianceTextView = rootView.findViewById(R.id.txtAllegiance);
        TextView abilitiesTextView = rootView.findViewById(R.id.txtAbilitiess);

        // Verifica si se ha proporcionado un titán para mostrar
        if (titan != null) {
            // Configura las vistas con los detalles del titán
            nameTextView.setText("Nombre: " + titan.getName());
            heightTextView.setText("Altura: " + titan.getHeight());
            allegianceTextView.setText("Lealtad: " + titan.getAllegiance());

            // Si `abilities` es una lista, puedes formatearla como desees, aquí se usa TextUtils para unirla con comas
            String abilitiesText = TextUtils.join(", ", titan.getAbilities());
            abilitiesTextView.setText("Habilidad: " + abilitiesText);

            // Glide u otra biblioteca para cargar imágenes
            Glide.with(requireContext()).load(titan.getImg()).into(imgTitanView);
        }

        return rootView;
    }

    private void showTitans(List<Titan> showsList) {
        RVDetalleTitanAdapter adapter = new RVDetalleTitanAdapter(showsList, titan -> {
            homeViewModel.addTitan(titan);
        });
    }
}