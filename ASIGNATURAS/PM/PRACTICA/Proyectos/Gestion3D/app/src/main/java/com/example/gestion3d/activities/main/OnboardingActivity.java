package com.example.gestion3d.activities.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.gestion3d.R;
import com.example.gestion3d.activities.auth.LoginActivity;
import com.example.gestion3d.adapters.OnboardingAdapter;

public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 viewPager; // Componente para deslizar las pantallas del Onboarding
    private LinearLayout dotsLayout; // Contenedor para los indicadores (puntos de navegación)
    private Button btnSkip; // Botón para saltar el Onboarding
    private int numPages = 8; // Número total de pantallas en el Onboarding
    private ImageView[] dots; // Array para manejar los puntos de navegación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtiene las preferencias de la aplicación
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean sesionIniciada = prefs.getBoolean("sesionIniciada", false);

        // Si la sesión ya está iniciada, redirige directamente a la actividad principal
        if (sesionIniciada) {
            startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
            finish();
            return;
        }

        // Oculta la ActionBar si está presente
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Asigna el diseño de la actividad
        setContentView(R.layout.main_onboarding);

        // Inicializa las vistas
        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotsLayout);
        btnSkip = findViewById(R.id.btnSkip);

        // Configura el adaptador del ViewPager para manejar las páginas del Onboarding
        viewPager.setAdapter(new OnboardingAdapter(this));

        // Configura los indicadores de navegación (puntos de progreso)
        setupIndicators(numPages);
        setActiveDot(0);

        // Cambia el estado del indicador cuando se desliza entre páginas
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setActiveDot(position);

                // Cambia el texto del botón en la última página
                btnSkip.setText(position == numPages - 1 ? "Continuar" : "Saltar");
            }
        });

        // Configura la acción del botón de "Saltar" o "Continuar"
        btnSkip.setOnClickListener(v -> goToLogin());
    }

    // Método para guardar que el Onboarding ha sido completado y redirigir al usuario
    private void goToLogin() {
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Guarda que el Onboarding ha sido completado para evitar mostrarlo nuevamente
        editor.putBoolean("onboardingCompletado", true);
        editor.apply();

        boolean sesionIniciada = prefs.getBoolean("sesionIniciada", false);

        // Si la sesión está iniciada, va a la pantalla principal, de lo contrario, al Login
        startActivity(new Intent(OnboardingActivity.this, sesionIniciada ? MainActivity.class : LoginActivity.class));
        finish();
    }

    // Método para crear los indicadores de navegación (puntos)
    private void setupIndicators(int count) {
        dots = new ImageView[count];
        dotsLayout.removeAllViews(); // Limpia cualquier vista anterior

        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.dot_inactive); // Imagen de punto inactivo

            // Define los márgenes entre los puntos
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            dots[i].setLayoutParams(params);

            // Agrega los puntos al contenedor de indicadores
            dotsLayout.addView(dots[i]);
        }
    }

    // Método para cambiar el estado del indicador activo
    private void setActiveDot(int index) {
        for (int i = 0; i < dots.length; i++) {
            // Si el índice coincide con la página actual, se resalta el punto
            dots[i].setImageResource(i == index ? R.drawable.dot_active : R.drawable.dot_inactive);
        }
    }
}