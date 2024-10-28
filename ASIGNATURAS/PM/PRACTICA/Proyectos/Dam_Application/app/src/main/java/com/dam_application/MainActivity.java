package com.dam_application;

import android.content.Intent;
import android.os.Bundle; // Importa la clase Bundle, que permite pasar datos entre actividades.
import android.view.View; // Importa la clase View para manejar eventos de clic.

import androidx.activity.EdgeToEdge; // Importa la funcionalidad EdgeToEdge.
import androidx.appcompat.app.AppCompatActivity; // Importa la clase base para actividades de la app.

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_actividad_siguiente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActividadMain.this, "Vamos a otra actividad", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, FileActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_actividad_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }
}

