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
    File ficheroJson;

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

        btn_leerJson.setOnClickListener(v -> ficheroJson = getFile(JsonParserActivity.this, "poliza.json"));
        btn_mostrarJson.setOnClickListener(v -> showFile(JsonParserActivity.this, ficheroJson));
    }

    private void storeJson(Context context, String fileName) {
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
                fos.write(buffer, 0, length);
            }
            fos.flush();
            fos.close();
            is.close();
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

//-----------------------------------  FUNCIONES  DEPRECIADAS  -----------------------------------//


    /**
     * <p>El proposito de esta funci&oacute;n es poder probar el antiguo funcionamiento de la actividad,
     * antes de la inclusi&oacute;n de un fichero JSON externo en {@code assets}, el cual implicaba
     * tener que crear el fichero JSON desde 0 dentro de la propia actividad (similar en concepto a
     * {@code  FileActivity}).</p>
     *
     * <p>Para provar el funcinamiento de esta y las otras 2 funciones internas ya depreciadas
     * ({@link #createJson(Context)} y {@link #saveJson(Context, String, JSONObject)}), solo se tiene
     * que sustituir la llama a la funci&oacute;n {@link #saveJson(Context, String, JSONObject)} por
     * una llamada a esta funci&aacute;n sin alterar los par&aacute;metros ya existentes (basta con
     * cambiar el nombre de la funci&oacute;n y listo).</p>
     *
     * @param context  el contexto del cual se obtiene el directorio raiz de ficheros de la actividad
     * @param fileName el nombre del fichero JSON a crear y guardar
     */
    private void alpha_storeJason(Context context, String fileName) {
        JSONObject persona = createJson(context);
        saveJson(context, fileName, persona);
    }

    /**
     * <p>Esta funci&oacute;n se utilizar&iacute;a para guardar, en el dir raiz de ficheros de la app,
     * el fichero JSON a mostrar y tambien a&ntilde;adir al fichero JSON el contenido correspondiente
     * al mismo.</p>
     *
     * <p>Esta funci&oacute;n trabaja en tandem junto a {@link #createJson(Context)} para almacenar
     * el JSON utilizado en la actividad.</p>
     *
     * <p>Para probar el funcionamiento de esta funci&oacute;n, refierase a la documentaci&oacute;n
     * de {@link #alpha_storeJason(Context, String)}. </p>
     *
     * @param context  el contexto del cual se obtiene el directorio raiz de ficheros de la actividad
     * @param fileName el nombre del fichero JSON a crear y guardar
     * @param persona  el JSONObject con la info que contendr&aacute; el fichero JSON
     */
    private void saveJson(Context context, String fileName, JSONObject persona) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(persona.toString(2));
            fileWriter.close();
        } catch (IOException | JSONException e) {
            Toast.makeText(context, "Error almacenando el JSON.", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>Esta funci&oacute;n se utilizar&iacute;a para crear la info contenida del JSON a guardar y
     * mostrar por pantalla, similar a lo hecho en {@code FileActivity} pero sin el contenido
     * aleatorizado.</p>
     *
     * <p>Esta funci&oacute;n trabaja en tandem junto a {@link #saveJson(Context, String, JSONObject)}
     * para almacenar el JSON utilizado en la actividad.</p>
     *
     * <p>Para probar el funcionamiento de esta funci&oacute;n, refierase a la documentaci&oacute;n
     * de {@link #alpha_storeJason(Context, String)}. </p>
     *
     * @return una instancia de JSONObject con toda la info que conformar&aacute; el contenido del
     *         fichero JSON
     */
    @NonNull
    private JSONObject createJson(Context context) {
        try {
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
        } catch (JSONException e) {
            Toast.makeText(context, "Error creando el contenido del JSON.", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}
