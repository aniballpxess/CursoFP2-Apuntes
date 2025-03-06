package com.example.usobasedatos;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    // Declaración de los botones
    private Button toApiButton;
    private Button toDosAPisButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Configuración para diseño de borde a borde
        setContentView(R.layout.activity_navigation); // Usa el layout correspondiente

        // Ajustar las barras de sistema (barra de estado, barra de navegación)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular los botones con los elementos en el layout
        toApiButton = findViewById(R.id.buttonApi); // Asegúrate de que el ID en el XML sea correcto
        toDosAPisButton = findViewById(R.id.buttonDosApis); // Asegúrate de que el ID en el XML sea correcto

        // Configurar el escucha de clics para los botones
        toApiButton.setOnClickListener(this);
        toDosAPisButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Manejar clics en los botones
        Intent intent;

        if (view.getId() == R.id.buttonApi) {
            // Navegar a la actividad de la API
            intent = new Intent(this, APIMercadonaActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.buttonDosApis) {
            // Navegar a la actividad de SQLite
            intent = new Intent(this, DosAPIsMercadonaActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Opción desconocida", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
