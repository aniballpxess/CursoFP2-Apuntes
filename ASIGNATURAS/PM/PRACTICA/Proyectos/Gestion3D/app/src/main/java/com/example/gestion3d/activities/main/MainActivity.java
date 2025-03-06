package com.example.gestion3d.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.gestion3d.R;
import com.example.gestion3d.activities.gestion.GestionClientesActivity;
import com.example.gestion3d.activities.gestion.GestionMaterialesActivity;
import com.example.gestion3d.activities.gestion.GestionOrdenesActivity;
import com.example.gestion3d.activities.gestion.GestionProductosActivity;
import com.example.gestion3d.fragments.GestionFragment;
import com.example.gestion3d.fragments.EstadisticasFragment;
import com.example.gestion3d.fragments.PerfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment gestionFragment, estadisticasFragment, perfilFragment, activeFragment;
    private static final String TAG = "MainActivity"; // Etiqueta para logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_principal);

        Log.d(TAG, "onCreate: MainActivity iniciada correctamente");

        // Inicializar NavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Inicializar fragmentos
        gestionFragment = new GestionFragment();
        estadisticasFragment = new EstadisticasFragment();
        perfilFragment = new PerfilFragment();
        activeFragment = gestionFragment;

        // Agregar los fragmentos una sola vez
        fragmentManager.beginTransaction()
                .add(R.id.container, gestionFragment, "gestion")
                .add(R.id.container, estadisticasFragment, "estadisticas").hide(estadisticasFragment)
                .add(R.id.container, perfilFragment, "perfil").hide(perfilFragment)
                .commit();

        Log.d(TAG, "Fragmentos inicializados y agregados correctamente");

        // Configurar navegación inferior
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_dashboard && activeFragment != gestionFragment) {
                Log.d(TAG, "Se seleccionó: Gestión");
                Toast.makeText(this, "Cambiando a Gestión", Toast.LENGTH_SHORT).show();
                switchFragment(gestionFragment, R.id.nav_dashboard);
            } else if (item.getItemId() == R.id.nav_estadisticas && activeFragment != estadisticasFragment) {
                Log.d(TAG, "Se seleccionó: Estadísticas");
                Toast.makeText(this, "Cambiando a Estadísticas", Toast.LENGTH_SHORT).show();
                switchFragment(estadisticasFragment, R.id.nav_estadisticas);
            } else if (item.getItemId() == R.id.nav_perfil && activeFragment != perfilFragment) {
                Log.d(TAG, "Se seleccionó: Perfil");
                Toast.makeText(this, "Cambiando a Perfil", Toast.LENGTH_SHORT).show();
                switchFragment(perfilFragment, R.id.nav_perfil);
            }
            return true;
        });

        // Establecer pantalla inicial
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_dashboard);
            Log.d(TAG, "Pantalla inicial configurada en 'Gestión'");
        }

        // Si se necesita refrescar el perfil después de un cambio, se hace el cambio automáticamente
        if (getIntent().getBooleanExtra("refreshPerfil", false)) {
            Log.d(TAG, "Se recibió solicitud para refrescar el perfil, cambiando a PerfilFragment");
            switchFragment(perfilFragment, R.id.nav_perfil);
        }
    }

    // Método para cambiar fragmentos sin perder su estado
    private void switchFragment(Fragment fragment, int navId) {
        if (fragment != activeFragment) {
            fragmentManager.beginTransaction()
                    .hide(activeFragment)
                    .show(fragment)
                    .commit();
            activeFragment = fragment;
            bottomNavigationView.setSelectedItemId(navId);

            Log.d(TAG, "Fragment cambiado a: " + fragment.getClass().getSimpleName());
        }
    }
}
