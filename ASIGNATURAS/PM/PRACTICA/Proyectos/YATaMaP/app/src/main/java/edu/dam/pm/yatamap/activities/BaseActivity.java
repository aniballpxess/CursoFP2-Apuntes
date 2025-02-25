package edu.dam.pm.yatamap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.dam.pm.yatamap.R;
import edu.dam.pm.yatamap.handlers.SPHelper;

public class BaseActivity extends AppCompatActivity {

    private FrameLayout contentFrame;
    protected SPHelper spHelper;
    protected MaterialToolbar topAppBar;
    protected BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("BASE", "Load Start");
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.base), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        contentFrame = findViewById(R.id.content_frame);
        spHelper = new SPHelper(this);
        topAppBar = findViewById(R.id.top_toolbar);
        bottomNav = findViewById(R.id.bottom_navigation);

        spHelper.setupDefaultNightMode();

        setupTopAppBar(getString(R.string.home_title));
        setupBottomNavigation();
        Log.d("BASE", "Load Finish");
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View contentView = inflater.inflate(layoutResID, null);
        contentFrame.addView(contentView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_night_mode) {
            spHelper.changeNightMode();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setupTopAppBar(String title) {
        topAppBar.setTitle(title);
        setSupportActionBar(topAppBar);
    }

    protected void setToolbarTitle(String title) {
        if (topAppBar != null) {
            topAppBar.setTitle(title);
        }
    }

    protected void setupBottomNavigation() {
        {
            bottomNav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(this, HomeActivity.class));
                }
                if (itemId == R.id.nav_tasks) {
                    startActivity(new Intent(this, TasksActivity.class));
                }
                if (itemId == R.id.nav_team) {
                    startActivity(new Intent(this, TeamActivity.class));
                }
                if (itemId == R.id.nav_settings) {
                    startActivity(new Intent(this, SettingsActivity.class));
                }

                finish();
                return true;
            });
        }
    }
}