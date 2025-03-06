package com.example.mcproyecto;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;


public class Calendario extends AppCompatActivity {

    private CalendarView calendarioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendario);


        calendarioView = findViewById(R.id.calendarioView);

        calendarioView.setOnDateChangeListener((view, año, mes, dia) -> {

            mes = mes + 1;

            String mensaje = "Fecha seleccionada: " + dia + "/" + mes + "/" + año;


            // Añadimos las festividades con emojis
            if (dia == 25 && mes == 12) {
                mensaje += " 🎄 - ¡Feliz Navidad!";
            } else if (dia == 1 && mes == 1) {
                mensaje += " 🎉 - ¡Feliz Año Nuevo!";
            } else if (dia == 5 && mes == 5) {
                mensaje += " 💐 - ¡Feliz Día de la Madre!";
            } else if (dia == 31 && mes == 10) {
                mensaje += " 🎃 - ¡Feliz Halloween!";
            }

            Toast.makeText(Calendario.this, mensaje, Toast.LENGTH_SHORT).show();


        });

    }


}