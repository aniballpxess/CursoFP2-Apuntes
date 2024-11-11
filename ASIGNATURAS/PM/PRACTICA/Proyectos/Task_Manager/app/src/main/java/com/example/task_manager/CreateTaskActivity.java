package com.example.task_manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
            if (taskName_value.isEmpty() || taskType_value.isEmpty()) {
                Toast.makeText(CreateTaskActivity.this, "Error creando archivo.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}