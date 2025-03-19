package com.example.equiposfutbol;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EquipoAdapter equipoAdapter;
    private List<Equipo> listaEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaEquipos = new ArrayList<>();
        cargarEquipos();

        equipoAdapter = new EquipoAdapter(listaEquipos);
        recyclerView.setAdapter(equipoAdapter);
    }

    private void cargarEquipos() {
        listaEquipos.add(new Equipo("Real Madrid", R.drawable.real_madrid));
        listaEquipos.add(new Equipo("FC Barcelona", R.drawable.barcelona));
        listaEquipos.add(new Equipo("Atlético de Madrid", R.drawable.atletico_madrid));
        listaEquipos.add(new Equipo("Sevilla FC", R.drawable.sevilla));
        listaEquipos.add(new Equipo("Real Sociedad", R.drawable.real_sociedad));
        listaEquipos.add(new Equipo("Villarreal CF", R.drawable.villarreal));
        listaEquipos.add(new Equipo("Real Betis", R.drawable.real_betis));
    }
}
