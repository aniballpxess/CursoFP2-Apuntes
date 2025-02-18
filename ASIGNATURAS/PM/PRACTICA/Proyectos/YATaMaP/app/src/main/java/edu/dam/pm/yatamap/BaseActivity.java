package edu.dam.pm.yatamap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.dam.pm.yatamap.handlers.SPHelper;

public class BaseActivity extends AppCompatActivity {

    private FrameLayout contentFrame;
    protected SPHelper spHelper;
    protected MaterialToolbar topAppBar;
    protected BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.base), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        EdgeToEdge.enable(this);

        contentFrame = findViewById(R.id.content_frame);
        spHelper = new SPHelper(this);
        topAppBar = findViewById(R.id.top_toolbar);
        bottomNav = findViewById(R.id.bottom_navigation);

        int defaultNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        if (spHelper.isNightModeEnabled()) {
            defaultNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        }
        boolean defaultNeedsChange = AppCompatDelegate.getDefaultNightMode() != defaultNightMode;
        if (defaultNeedsChange) {
            AppCompatDelegate.setDefaultNightMode(defaultNightMode);
        }

        setupTopAppBar(getString(R.string.home_title));
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
            boolean changeMode = !spHelper.isNightModeEnabled();
            spHelper.setNightMode(changeMode);
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