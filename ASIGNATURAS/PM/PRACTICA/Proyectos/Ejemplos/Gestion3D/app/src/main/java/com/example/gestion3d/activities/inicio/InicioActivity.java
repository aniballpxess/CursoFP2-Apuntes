package com.example.gestion3d.activities.inicio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.gestion3d.R;
import com.example.gestion3d.activities.auth.LoginActivity;
import com.example.gestion3d.activities.main.MainActivity;
import com.example.gestion3d.activities.main.OnboardingActivity;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activa el diseño de pantalla completa utilizando EdgeToEdge
        EdgeToEdge.enable(this);

        // Establece el diseño de la actividad utilizando el layout definido en XML
        setContentView(R.layout.inicio_pantalla);

        // Oculta la Action Bar si está presente
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Ajusta los márgenes del contenido para evitar solapamientos con la barra de estado o navegación
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtiene las preferencias de la aplicación para verificar el estado del usuario
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean onboardingCompletado = prefs.getBoolean("onboardingCompletado", false); // Verifica si el usuario completó el onboarding
        boolean sesionIniciada = prefs.getBoolean("sesionIniciada", false); // Verifica si la sesión está iniciada

        // Muestra la pantalla de inicio por 1 segundo antes de redirigir a la actividad correspondiente
        new Handler().postDelayed(() -> {
            if (sesionIniciada) {
                // Si la sesión está iniciada, dirige al usuario a la pantalla principal
                startActivity(new Intent(InicioActivity.this, MainActivity.class));
            } else if (onboardingCompletado) {
                // Si el onboarding fue completado pero la sesión no está iniciada, dirige a la pantalla de login
                startActivity(new Intent(InicioActivity.this, LoginActivity.class));
            } else {
                // Si el onboarding no ha sido completado, dirige a la pantalla de Onboarding
                startActivity(new Intent(InicioActivity.this, OnboardingActivity.class));
            }
            // Finaliza la actividad actual para que el usuario no pueda volver atrás
            finish();
        }, 1000); // Espera de 1000ms (1 segundo) antes de la redirección
    }
}