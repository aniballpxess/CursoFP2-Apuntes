package com.dam_application;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class JsonParserActivity extends AppCompatActivity {
    Button btn_leerJson;
    Button btn_mostrarJson;
    TextView tv_contenidoJson;
    File archivoJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_json_parser);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.json), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_leerJson = findViewById(R.id.btn_jsonRead);
        btn_mostrarJson = findViewById(R.id.btn_jsonShow);
        tv_contenidoJson = findViewById(R.id.tv_jsonContent);

        // Establece un metodo para moverse por el contenido (en este caso un scroll lateral)
        tv_contenidoJson.setMovementMethod(new ScrollingMovementMethod());

        createAndFillJsonFile(JsonParserActivity.this, "sample_data.json");

        btn_leerJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                archivoJson = getFile(JsonParserActivity.this, "sample_data.json");
            }
        });

        btn_mostrarJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void createAndFillJsonFile(Context context, String fileName) {
        try {
            // Create a JSON object and populate it with data
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "Anibal");
            jsonObject.put("age", 30);
            jsonObject.put("city", "San Francisco");

            // Create a nested JSON object (example)
            JSONObject address = new JSONObject();
            address.put("street", "1234 Example St");
            address.put("zip", "94110");

            // Add the nested object to the main JSON
            jsonObject.put("address", address);

            // Write JSON object to internal storage file
            File file = new File(context.getFilesDir(), fileName);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString(4)); // Pretty print with 4-space indentation
            fileWriter.close();

            System.out.println("JSON file created at: " + file.getAbsolutePath());
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
    }

    private File getFile(Context context, String fileName) {
        return new File(context.getFilesDir(), fileName);
    }

    private void showFile(Context context, String fileName) {
        File file = new File(getFilesDir(), fileName);

    }
}