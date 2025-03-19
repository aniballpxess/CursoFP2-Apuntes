package com.example.task_manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PriorityTasksActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_priority_tasks);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.priority), (v, insets) -> {
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
        TextView task = findViewById(R.id.VIP_Task1);
        task.setText(taskName);
    }
}