package com.dam_application;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

public class PopupActivity extends BaseActivity {

    private Button btn_activarPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        btn_activarPopup = findViewById(R.id.btn_activarPopup);

        btn_activarPopup.setOnClickListener( v -> {
            Log.i("USER_ACTION", "Botón de error pulsado.");
            mostrarVentanaError();
        });
    }

    private void mostrarVentanaError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INTERNAL ERROR")
                .setIcon(R.drawable.ic_error_msg)
                .setMessage("Se ha producido un error en la app.\nQuieres saber más al respecto?.")
                .setPositiveButton("Sí", (dialog, id) -> {
                    abrirPaginaWeb("https://developer.android.com/google/play/integrity/error-codes#iErr_100");
                    Log.e("FATAL_ERROR", "Se ha producido un error insalvable.");
                    finishAffinity();
                })
                .setNegativeButton("No", (dialog, id) -> {
                    Log.e("FATAL_ERROR", "Se ha producido un error insalvable.");
                    finishAffinity();
                });
        builder.create().show();
    }
}