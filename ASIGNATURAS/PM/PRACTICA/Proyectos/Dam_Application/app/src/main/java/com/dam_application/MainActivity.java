package com.dam_application;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_actividadFichero;
    private Button btn_actividadCalculadora;
    private Button btn_actividadPaleta;
    private Button btn_actividadJson;
    private TextView tv_finClases;
    private Button btn_actividadTest;
    private final String FECHA_FIN_CLASES = "2024-12-20 21:20:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_actividadFichero = findViewById(R.id.btn_actividadFichero);
        btn_actividadCalculadora = findViewById(R.id.btn_actividadCalculadora);
        btn_actividadPaleta = findViewById(R.id.btn_actividadPaleta);
        btn_actividadJson = findViewById(R.id.btn_actividadJson);
        btn_actividadTest = findViewById(R.id.btn_actividadTest);
        tv_finClases = findViewById(R.id.clk_finClases);

        btn_actividadFichero.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FileActivity.class);
            startActivity(intent);
        });

        btn_actividadCalculadora.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

        btn_actividadPaleta.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PaletteActivity.class);
            startActivity(intent);
        });

        btn_actividadJson.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, JsonParserActivity.class);
            startActivity(intent);
        });

        btn_actividadTest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
        });

        getCountdownGoing();
    }

    private void getCountdownGoing() {
        long t_restante = tiempoRestante();
        if (t_restante > 0) {
            comenzarCuentaAtras(t_restante);
        }
    }

    private long tiempoRestante() {
        long t_objetivo = parsearTiempo(FECHA_FIN_CLASES);
        if (t_objetivo < 0)
        {
            tv_finClases.setText("Error en formato de fecha.");
            return -1;
        }
        long t_actual = System.currentTimeMillis();
        long t_restante = t_objetivo - t_actual;
        if (t_restante < 0)
        {
            tv_finClases.setText("¡Ya se han acabado las clases!");
            return 0;
        }
        return t_restante;
    }

    private long parsearTiempo(String str_fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sdf.setLenient(false);
        Date date = sdf.parse(str_fecha, new ParsePosition(0));
        if (date == null)
        {
            return -1;
        }
        return date.getTime();
    }


    private void comenzarCuentaAtras(long ms_duracion) {
        new CountDownTimer(ms_duracion, 1000) {
            @Override
            public void onTick(long ms_tiempoRestante) {
                long segundos = ms_tiempoRestante / 1000;
                long minutos = segundos / 60;
                long horas = minutos / 60;
                long dias = horas / 24;
                horas %= 24;
                minutos %= 60;
                segundos %= 60;
                tv_finClases.setText(
                        String.format(Locale.getDefault(),
                                      "%02d DÍAS RESTANTES\n %02d:%02d:%02d",
                                      dias, horas, minutos, segundos));
            }

            @Override
            public void onFinish() {
                tv_finClases.setText("¡Ya se han acabado las clases!");
            }
        }.start();
    }
}

