package com.example.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontramos el RecyclerView por ID
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Configuramos el RecyclerView con un LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Creamos la lista de elementos
        List<Item> listaDeItems = new ArrayList<>();
        listaDeItems.add(new Item("Bogotá", R.drawable.bogota_img));
        listaDeItems.add(new Item("Madrid", R.drawable.madrid_img));
        listaDeItems.add(new Item("London", R.drawable.london_img));
        listaDeItems.add(new Item("New York", R.drawable.ny_img));
        listaDeItems.add(new Item("Madrid", R.drawable.madrid_img));
        listaDeItems.add(new Item("London", R.drawable.london_img));
        listaDeItems.add(new Item("Bogotá", R.drawable.bogota_img));
        listaDeItems.add(new Item("New York", R.drawable.ny_img));

        // Creamos el adaptador y lo asignamos al RecyclerView
        MyRVAdapter adaptador = new MyRVAdapter(listaDeItems);
        recyclerView.setAdapter(adaptador);
    }
}