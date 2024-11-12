package com.example.task_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateTaskActivity extends AppCompatActivity {

    private String taskName_value;
    private String taskType_value;
    private EditText taskName_input;
    private RadioGroup taskType_input;
    private Button btn_createTask;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.create), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        taskName_input = findViewById(R.id.et_taskName);
        taskType_input = findViewById(R.id.rg_taskType);
        btn_createTask = findViewById(R.id.btn_createTask);

        taskType_input.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedType = findViewById(checkedId);
            taskType_value = selectedType.getText().toString();
        });

        btn_createTask.setOnClickListener(v -> {
            taskName_value = taskName_input.getText().toString();
            if (taskName_value.isBlank()) {
                Toast.makeText(CreateTaskActivity.this, "Error creando archivo.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (taskType_value == null) {
                Toast.makeText(CreateTaskActivity.this, "Error creando archivo.", Toast.LENGTH_SHORT).show();
                return;
            }
            switch (taskType_value) {
                case "Prioritaria":
                    intent = new Intent(CreateTaskActivity.this, PriorityTasksActivity.class);
                    break;
                case "A largo plazo":
                    intent = new Intent(CreateTaskActivity.this, LongTermTasksActivity.class);
                    break;
            }
            intent.putExtra("TASK_NAME", taskName_value);
            intent.putExtra("TASK_TYPE", taskType_value);
            startActivity(intent);
            taskName_input.setText("");
            taskType_input.clearCheck();
        });
    }
}