package com.dam_application;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_file);

        findViewById(R.id.btn_create_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile("file.tmp", "Texto de ejemplo.");
            }
        });

        findViewById(R.id.btn_fill_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillFile("file.tmp", 500);
            }
        });

        findViewById(R.id.btn_show_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFile("file.tmp");
            }
        });

        findViewById(R.id.btn_clear_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FileActivity.this, "Contenido borrado.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createFile(String fileName, String fileContent) {
        File file = new File(getFilesDir(), fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContent.getBytes());
            Toast.makeText(FileActivity.this, "Archivo creado.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error creando archivo.", Toast.LENGTH_SHORT).show();
        }
    }

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

    private void showFile(String fileName) {
        StringBuilder content = new StringBuilder();
        File file = new File(getFilesDir(), fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                content.append(line);
            }
            Toast.makeText(FileActivity.this, "Contenido mostrado.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(FileActivity.this, "Error mostrando contenido.", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateRandomWords(int words) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int WORD_LENGTH = 8;
        SecureRandom random = new SecureRandom();
        StringBuilder text = new StringBuilder(WORD_LENGTH);

        for (int i = 0; i < words; i++) {
            for (int j = 0; j < WORD_LENGTH; j++) {
                int index = random.nextInt(CHARACTERS.length());
                text.append(CHARACTERS.charAt(index));
            }
            if (i == words - 1) {
                text.append('.');
            } else {
                text.append(' ');
            }
        }
        return text.toString();
    }
}