package com.example.mcproyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuenoMalo extends AppCompatActivity {

    private EditText usernameInput;
    private Button option1Button, option2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bueno_malo); // Replace with your layout file

        usernameInput = findViewById(R.id.username_input);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);

        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                // Perform registration logic here (e.g., save username to database)
                Toast.makeText(BuenoMalo.this, "Option 1 clicked! Username: " + username, Toast.LENGTH_SHORT).show();
            }
        });

        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuenoMalo.this, "Option 2 clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}