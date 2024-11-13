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

    private Intent intent;
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

        btn_createTask.setOnClickListener(v -> {
            taskName_value = taskName_input.getText().toString();
            if (taskName_value.isBlank()) {
                Toast.makeText(CreateTaskActivity.this, "Introduce un nombre para la tarea.", Toast.LENGTH_SHORT).show();
                return;
            }
            int selectedId = taskType_input.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(CreateTaskActivity.this, "Introduce el tipo de tarea.", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedType = findViewById(selectedId);
            taskType_value = selectedType.getText().toString();
            switch (taskType_value) {
                case "Prioritaria":
                    intent = new Intent(CreateTaskActivity.this, PriorityTasksActivity.class);
                    break;
                case "A largo plazo":
                    intent = new Intent(CreateTaskActivity.this, LongTermTasksActivity.class);
                    break;
            }
            intent.putExtra("com.example.task_manager.TASK_NAME", taskName_value);
            startActivity(intent);
            taskName_input.setText("");
            taskType_input.clearCheck();
        });
    }
}