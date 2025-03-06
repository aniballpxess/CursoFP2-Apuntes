package com.example.mcproyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class BolaComida extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bola_comida); // Asegúrate de que sea el layout correcto.

        Button btnVolver = findViewById(R.id.btn_volver); // Identifica el botón en el XML.
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finaliza (cierra) la actividad actual.
                finish();
            }
        });
    }
}