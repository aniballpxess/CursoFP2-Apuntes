package com.dam_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends BaseActivity {

    EditText et_num1, et_num2;
    TextView tv_resultado;
    Button btn_calcular;
    ImageButton btn_cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        et_num1 = findViewById(R.id.et_numero1);
        et_num2 = findViewById(R.id.et_numero2);
        tv_resultado = findViewById(R.id.tv_resultado);
        btn_calcular = findViewById(R.id.btn_calcular);
        btn_cerrar = findViewById(R.id.btn_cerrar);

        btn_calcular.setOnClickListener(v -> {
            double n1, n2, resultado;
            n1 = Double.parseDouble(et_num1.getText().toString());
            n2 = Double.parseDouble(et_num2.getText().toString());
            resultado = n1 + n2;
            tv_resultado.setText(String.valueOf(resultado));

        });

        btn_cerrar.setOnClickListener(v -> {
            Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}