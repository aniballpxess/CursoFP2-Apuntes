package com.example.mcproyecto; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcproyecto.R;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Replace with your layout file

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent for Button 1
                Intent intent1 = new Intent(MainActivity.this, arbol.class); // Replace GoodActivity with your target activity
                startActivity(intent1);
                return;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent for Button 2
                Intent intent2 = new Intent(MainActivity.this, BadActivity.class); // Replace BadActivity with your target activity
                startActivity(intent2);
            }
        });
    }
}