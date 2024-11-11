package com.example.task_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_goCreate;
    Button btn_goPriority;
    Button btn_goLongTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_goCreate = findViewById(R.id.btn_create);
        btn_goPriority = findViewById(R.id.btn_priority);
        btn_goLongTerm = findViewById(R.id.btn_longTerm);

        btn_goCreate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);
            startActivity(intent);
        });
        btn_goPriority.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PriorityTasksActivity.class);
            startActivity(intent);
        });
        btn_goLongTerm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LongTermTasksActivity.class);
            startActivity(intent);
        });
    }
}