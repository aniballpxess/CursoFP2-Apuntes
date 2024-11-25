package com.dam_application;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {

    private FrameLayout contentFrame;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        contentFrame = findViewById(R.id.contentFrame);
        toolbar = findViewById(R.id.toolbar);

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
