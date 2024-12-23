package com.dam_application;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatDelegate;

public class PaletteActivity extends BaseActivity {

    private static final String PREFS_NAME = "theme_prefs";
    private static final String THEME_KEY = "isNightMode";

    private ToggleButton tb_modoNocturnoDiurno;

    private RatingBar rtb_valoracionUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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

        tb_modoNocturnoDiurno = findViewById(R.id.tb_modoNocturnoDiurno);
        rtb_valoracionUsuario = findViewById(R.id.rtb_opinionUsuario);

        rtb_valoracionUsuario.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser) {
                guardarValoracion(rating);
                String message = getCustomMessage(rating);
                mostrarPopup(message);
            }
        });

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

    private void guardarValoracion(float valoracion) {
        System.out.println("Rating saved: " + valoracion);
    }

    private String getCustomMessage(float valoracion) {
        if (valoracion <= 1) {
            return "Joder, tampoco hay que pasarse tanto, esto es un proyecto de un alumno de DAM.";
        } else if (valoracion <= 3) {
            return "Lo entiendo. Me queda mucho para hacer que esto sea algo funcionalmente aceptable.";
        } else if (valoracion <= 4) {
            return "Me agrada que te haya gustado tanto la app. Aun así queda mucho por mejorar. Seguiré trabajando en ello.";
        } else {
            return "¿Wow, wow, wow, wooooooooow!¿Seguro que no has pulsado sin querer?";
        }
    }

}