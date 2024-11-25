package com.dam_application;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaletteActivity extends BaseActivity {

    private static final String PREFS_NAME = "theme_prefs";
    private static final String THEME_KEY = "isNightMode";

    private ToggleButton tb_modoNocturnoDiurno;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // -------------------------------------------------------------------------------------- //
        // TODO - MODO NOCTURNO
        //  introducir esto en la ToolBar/Menu de la actividad base porque esto es muy feo
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isNightMode = preferences.getBoolean(THEME_KEY, false);
        if (isNightMode)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        // -------------------------------------------------------------------------------------- //
        setContentView(R.layout.activity_palette);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pallete), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        tb_modoNocturnoDiurno = findViewById(R.id.tb_modoNocturnoDiurno);

        // -------------------------------------------------------------------------------------- //
        // TODO - MODO NOCTURNO
        //  codigo extra a ser implementado junto con el superior en la Toolbar/Menu
        tb_modoNocturnoDiurno.setChecked(isNightMode);

        tb_modoNocturnoDiurno.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(THEME_KEY, isChecked);
            editor.apply();
        });
        // -------------------------------------------------------------------------------------- //
    }
}