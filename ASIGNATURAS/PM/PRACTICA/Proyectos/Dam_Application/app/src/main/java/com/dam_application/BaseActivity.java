package com.dam_application;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.base), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.toolbar);
        contentFrame = findViewById(R.id.contentFrame);

        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (contentFrame != null) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View contentView = inflater.inflate(layoutResID, null);
            contentFrame.addView(contentView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO - funcionalidad extra de cada opción
        if (item.getItemId() == R.id.action_settings) {
            // Funcionalidad extra aquí
            return true;
        }
        if (item.getItemId() == R.id.action_help) {
            // Funcionalidad extra aquí
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
