package com.dam_application;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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

        storeJson(JsonParserActivity.this, "poliza.json");

        btn_leerJson.setOnClickListener(v -> archivoJson = getFile(JsonParserActivity.this, "poliza.json"));
        btn_mostrarJson.setOnClickListener(v -> showFile(JsonParserActivity.this, archivoJson));
    }

    private void storeJson(Context context, String fileName) {
        // JSONObject persona = createJson();
        // saveJson(context, fileName, persona);
        File file = new File(context.getFilesDir(), fileName);
        InputStream is;
        FileOutputStream fos;
        byte[] buffer = new byte[1024];
        int length;
        try {
            is = context.getAssets().open(fileName);
            fos = new FileOutputStream(file, true);
            while (true) {
                length = is.read(buffer);
                if (length < 0) {
                    break;
                }
                fos.write(buffer, 0 ,length);
            }
            fos.flush();
        } catch (IOException e) {
            Toast.makeText(context, "Error creando el JSON.", Toast.LENGTH_SHORT).show();
        }
    }

    private File getFile(Context context, String fileName) {
        return new File(context.getFilesDir(), fileName);
    }

    private void showFile(Context context, File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                content.append(line);
                content.append(System.lineSeparator());
            }
            tv_contenidoJson.setText(content);
        } catch (IOException e) {
            Toast.makeText(context, "Error leyendo el JSON.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveJson(Context context, String fileName, JSONObject persona) throws IOException, JSONException {
        File file = new File(context.getFilesDir(), fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(persona.toString(2));
        fileWriter.close();
    }

    @NonNull
    private JSONObject createJson() throws JSONException {
        JSONObject direccion = new JSONObject();
        direccion.put("calle", "c/ Méjico 27");
        direccion.put("puerta", "5ºD");
        direccion.put("cod. postal", "28640");
        direccion.put("localidad", "San Fernando");
        direccion.put("provincia", "Madrid");

        JSONObject persona = new JSONObject();
        persona.put("nombre", "Anibal");
        persona.put("edad", 30);
        persona.put("nacionalidad", "español");
        persona.put("direccion", direccion);

        return persona;
    }
}
