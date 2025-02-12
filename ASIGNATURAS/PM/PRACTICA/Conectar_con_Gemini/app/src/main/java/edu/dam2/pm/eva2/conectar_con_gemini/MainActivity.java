package edu.dam2.pm.eva2.conectar_con_gemini;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.Part;
import com.google.ai.client.generativeai.type.TextPart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public class MainActivity extends AppCompatActivity {

    private GenerativeModel generativeModel;
    private EditText inputEditText;
    private TextView responseTextView;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        responseTextView = findViewById(R.id.responseTextView);
        sendButton = findViewById(R.id.sendButton);

        generativeModel = new GenerativeModel("gemini-pro", "MY_API_KEY");

        sendButton.setOnClickListener(v -> {
            String prompt = inputEditText.getText().toString();

            GenerateContentResponse response = null;
            new Thread(() -> {
                try {
                    runOnUiThread(() -> responseTextView.setText(response.getText()));
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}