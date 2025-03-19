package com.example.gestion3d.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.gestion3d.R;
import com.example.gestion3d.activities.gestion.GestionMaterialesActivity;
import com.example.gestion3d.activities.gestion.GestionProductosActivity;
import com.example.gestion3d.activities.gestion.GestionOrdenesActivity;
import com.example.gestion3d.activities.gestion.GestionClientesActivity;

public class GestionFragment extends Fragment {

    private CardView cardMateriales, cardProductos, cardOrdenes, cardClientes;
    private static final String TAG = "GestionFragment"; // Etiqueta para logs

    public GestionFragment() {
        // Constructor vacío requerido por Fragment
    }

    // Inflar el layout del fragmento
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gestion, container, false);

        Log.d(TAG, "onCreateView: Fragmento GestionFragment inflado correctamente");

        // Enlazar CardView con el layout
        cardMateriales = view.findViewById(R.id.cardMateriales);
        cardProductos = view.findViewById(R.id.cardProductos);
        cardOrdenes = view.findViewById(R.id.cardOrdenes);
        cardClientes = view.findViewById(R.id.cardClientes);

        // Configurar eventos de clic con logs y Toasts
        cardMateriales.setOnClickListener(v -> {
            Log.d(TAG, "Navegando a GestionMaterialesActivity");
            Toast.makeText(getActivity(), "Abriendo Gestión de Materiales", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), GestionMaterialesActivity.class));
        });

        cardProductos.setOnClickListener(v -> {
            Log.d(TAG, "Navegando a GestionProductosActivity");
            Toast.makeText(getActivity(), "Abriendo Gestión de Productos", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), GestionProductosActivity.class));
        });

        cardOrdenes.setOnClickListener(v -> {
            Log.d(TAG, "Navegando a GestionOrdenesActivity");
            Toast.makeText(getActivity(), "Abriendo Gestión de Órdenes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), GestionOrdenesActivity.class));
        });

        cardClientes.setOnClickListener(v -> {
            Log.d(TAG, "Navegando a GestionClientesActivity");
            Toast.makeText(getActivity(), "Abriendo Gestión de Clientes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), GestionClientesActivity.class));
        });

        return view;
    }
}