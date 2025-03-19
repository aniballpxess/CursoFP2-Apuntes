package com.dam_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_actividad_anterior).setOnClickListener(v -> {
            Intent intent = new Intent(TestActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.toggleButton).setOnClickListener(v -> v.setActivated(!v.isActivated()));
    }
}