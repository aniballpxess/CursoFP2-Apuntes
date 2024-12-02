package com.dam_application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        super.setContentView(R.layout.activity_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.base), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        contentFrame = findViewById(R.id.contentFrame);

        setSupportActionBar(toolbar);

        // Para mostrar el botón Home (flecha para volver atrás) solo cuando haga falta (no en Main)
        if (deberiaPoderIrParaAtras()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (contentFrame != null) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View contentView = inflater.inflate(layoutResID, null);
            contentFrame.addView(contentView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO - funcionalidad extra de cada opción
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed(); // Te devuelve a la actividad anterior
            return true;
        }
        if (item.getItemId() == R.id.action_settings) {
            // Funcionalidad extra aquí
            return true;
        }
        if (item.getItemId() == R.id.action_help) {
            // Funcionalidad extra aquí
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean deberiaPoderIrParaAtras() {
        return true;
    }

    protected void mostrarPopup(String mensaje) {
        Toast.makeText(BaseActivity.this, mensaje, Toast.LENGTH_LONG).show();
    }

    protected void abrirPaginaWeb(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
