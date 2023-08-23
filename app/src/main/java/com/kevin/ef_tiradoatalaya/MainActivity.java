package com.kevin.ef_tiradoatalaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;
import com.kevin.ef_tiradoatalaya.databinding.ActivityMainBinding;
import com.kevin.ef_tiradoatalaya.data.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static String EMAIL = "EMAIL";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        sharedPreferences = getSharedPreferences(LoginActivity.SESSION_PREFERENCE, MODE_PRIVATE);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.tbPrincipal);
        addHomeFragment();
    }

    private void addHomeFragment() {
        getSupportFragmentManager().
                beginTransaction()
                .add(binding.titansListContainer.getId(), new HomeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favorite) {
            Snackbar.make(binding.getRoot(), "Favorite", Snackbar.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.logout) {
            showDialog();
            return true;
        }
        return false;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void logout() {
        sharedPreferences.edit().clear().apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}