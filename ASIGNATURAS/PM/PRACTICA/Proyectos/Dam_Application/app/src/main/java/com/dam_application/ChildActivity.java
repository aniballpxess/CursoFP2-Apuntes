package com.dam_application;

import static android.widget.Toast.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_child);

        findViewById(R.id.btn_actividad_anterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_actividad_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_info_pj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeText(ChildActivity.this, R.string.Scorch_info_msg, LENGTH_LONG).show();
            }
        });
    }
}