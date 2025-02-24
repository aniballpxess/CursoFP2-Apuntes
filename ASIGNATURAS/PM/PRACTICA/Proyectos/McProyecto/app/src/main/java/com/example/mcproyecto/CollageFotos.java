package com.example.mcproyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class CollageFotos extends AppCompatActivity {

    // IDs de las imágenes del collage
    private int[] imageIds = {
            R.drawable.navidad1,
            R.drawable.navidad2,
            R.drawable.navidad3,
            R.drawable.navidad4,
            R.drawable.navidad5,
            R.drawable.navidad6,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collagefotos);

        GridView gridView = findViewById(R.id.gridViewCollage);
        ImageAdapter adapter = new ImageAdapter(this, imageIds);
        gridView.setAdapter(adapter);

        // Añadir funcionalidad al botón "Volver"
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finaliza la actividad y vuelve a la anterior
                finish();
            }
        });
    }
}
