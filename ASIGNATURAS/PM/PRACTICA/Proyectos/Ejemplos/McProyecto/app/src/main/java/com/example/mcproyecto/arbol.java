package com.example.mcproyecto; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class arbol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arbol_activity); // Replace with your layout file name

        ImageButton bolauno = findViewById(R.id.bolauno);
        ImageButton bolados = findViewById(R.id.bolados);
        ImageButton bolatres = findViewById(R.id.bolatres);
        ImageButton bolacuatro = findViewById(R.id.bolacuatro);
        ImageButton bolacinco = findViewById(R.id.bolacinco);

        bolauno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(arbol.this, BolaComida.class)); // Replace BolaunoActivity with your target activity
            }
        });

        bolados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(arbol.this, Bola2.class)); // Replace BolaDosActivity with your target activity
            }
        });

        bolatres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(arbol.this, CollageFotos.class)); // Replace BolaDosActivity with your target activity
            }
        });

        bolacuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(arbol.this, Calendario.class)); // Replace BolaDosActivity with your target activity
            }
        });

        bolacinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(arbol.this, BolaEstrella.class)); // Replace BolaDosActivity with your target activity
            }
        });
    }

}