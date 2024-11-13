package com.example.task_manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LongTermTasksActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_long_term_tasks);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.long_term), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intent = getIntent();
        handleCreateTask();
    }

    private void handleCreateTask() {
        String taskName = intent.getStringExtra("com.example.task_manager.TASK_NAME");
        if (taskName == null) {
            return;
        }
        TextView task1 = findViewById(R.id.LT_Task1);
        task1.setText(taskName);
    }
}