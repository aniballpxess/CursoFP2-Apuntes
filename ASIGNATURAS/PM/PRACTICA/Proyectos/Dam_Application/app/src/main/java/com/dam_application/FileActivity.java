package com.dam_application;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;

public class FileActivity extends BaseActivity {

    TextView vistaContenido;
    // Se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_file);
        // PRUEBA
        vistaContenido = findViewById(R.id.file_content_view);
        vistaContenido.setMovementMethod(new ScrollingMovementMethod());

        // ------------------------------ ESCUCHADORES DE CADA BOTÓN -------------------------------
        findViewById(R.id.btn_create_file).setOnClickListener(v -> createFile("file.tmp", "Texto de ejemplo."));

        findViewById(R.id.btn_fill_file).setOnClickListener(v -> fillFile("file.tmp", 100));

        findViewById(R.id.btn_show_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFile("file.tmp");
            }
        });

        findViewById(R.id.btn_clear_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cleanClose("file.tmp"); }
        });
    }

    // ---------------------------------- FUNCIONES DE CADA BOTÓN ----------------------------------
    // Muestran mensajes para informar del exito o fracaso de la ejecución.
    // Como los parametros son fijos no se hace niguna comprobación sobre la validez de los mismos

    // Crea el archivo con el nombre dado en el Directorio de Ficheros y lo rellena con un texto generico que se le dé.
    private void createFile(String fileName, @NonNull String fileContent) {
        File file = new File(getFilesDir(), fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContent.getBytes());
            Toast.makeText(FileActivity.this, "Archivo creado.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(FileActivity.this, "Error creando archivo.", Toast.LENGTH_SHORT).show();
        }
    }

    // Rellena el fichero con el nombre dado (lo busca en el Directorio de Ficheros) de tantas palabras como se le estipule.
    private void fillFile(String fileName, int words) {
        File file = new File(getFilesDir(), fileName);
        String content = generateRandomWords(words);

        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            fos.write(content.getBytes());
            fos.write(System.lineSeparator().getBytes());
            Toast.makeText(FileActivity.this, "Archivo rellenado.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(FileActivity.this, "Error rellenado archivo.", Toast.LENGTH_SHORT).show();
        }
    }

    // Copia todas las lineas (parrafos) del fichero con el nombre dado a la vista de texto donde se muestra el contenido.
    private void showFile(String fileName) {
        StringBuilder content = new StringBuilder(); // No he usado SB hasta ahora pero sé inglés y queda mejor que una String a la que le vas añadiendo cosas
        File file = new File(getFilesDir(), fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Bucle para leer todas las lineas (parrafos) del fichero
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                content.append(line);
            }
            // Estaria bien que se explicara cual es la diferencia  ("inferencia" vs "cast a pelo")
            //((TextView) findViewById(R.id.file_content_view)).setText(content); // Version "cast"
            this.<TextView>findViewById(R.id.file_content_view).setText(content); // Version "inferencia"
            Toast.makeText(FileActivity.this, "Contenido mostrado.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(FileActivity.this, "Error mostrando contenido.", Toast.LENGTH_SHORT).show();
        }
    }

    // Hace lo siguiente:
    // 1. Limpia el contenido de la vista
    // 2. Elimina el archivo
    // 3. Confirma la eliminacion con un mensaje
    // 4. Inicia la actividad principal (menú con todas las demás actividades)
    // 5. Termina esta actividad
    private void cleanClose (String fileName) {
        File file = new File(getFilesDir(), fileName);

        this.<TextView>findViewById(R.id.file_content_view).setText("");
        file.delete();
        Toast.makeText(FileActivity.this, "Contenido borrado.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // -------------------------------------- FUNCIONES EXTRA --------------------------------------

    // Genera el numero de palabras indicado y las devuelve en una String.
    // palabra -> (8 caracteres aleatorios)
    private String generateRandomWords(int words) {
        // Caracteres permitidos por palabra
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Tamaño de las palabras
        final int WORD_LENGTH = 8;
        SecureRandom random = new SecureRandom(); // Mejor que Random
        StringBuilder text = new StringBuilder(WORD_LENGTH);

        // Bucle para generar las palabras
        for (int i = 0; i < words; i++) {
            // Bucle para generar cada palabra
            for (int j = 0; j < WORD_LENGTH; j++) {
                int index = random.nextInt(CHARACTERS.length());
                text.append(CHARACTERS.charAt(index));
            }
            if (i == words - 1) {
                text.append('.'); // Se incluye al final de la String
            } else {
                text.append(' '); // Se incluye entre palabras
            }
        }
        return text.toString();
    }
}