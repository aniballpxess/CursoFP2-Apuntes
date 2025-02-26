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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.dam.pm.yatamap.R;
import edu.dam.pm.yatamap.databinding.ActivityBaseBinding;
import edu.dam.pm.yatamap.handlers.SPHelper;

public class BaseActivity extends AppCompatActivity {

    private ActivityBaseBinding binding;
    private FrameLayout fragmentContainer;
    protected MaterialToolbar topAppBar;
    protected BottomNavigationView bottomNav;
    protected SPHelper spHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("BASE", "Load Start");

        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.base), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                            | WindowInsetsCompat.Type.displayCutout()
            );
           v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        binding = ActivityBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentContainer = binding.fragmentContainer;
        topAppBar = binding.topToolbar;
        bottomNav = binding.bottomNavigation;
        spHelper = new SPHelper(this);

        spHelper.setupDefaultNightMode();

        setSupportActionBar(topAppBar);
        topAppBar.setTitle(getString(R.string.home_title));

        setupBottomNavigation();

        Log.d("BASE", "Load Finish");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_night_mode) {
            spHelper.changeNightMode();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setupBottomNavigation() {
        {
            bottomNav.setSelectedItemId(R.id.nav_home);

            bottomNav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {

                    topAppBar.setTitle(getString(R.string.home_title));
                    return true;
                }
                if (itemId == R.id.nav_tasks) {

                    topAppBar.setTitle(getString(R.string.tasks_title));
                    return true;
                }
                if (itemId == R.id.nav_team) {

                    topAppBar.setTitle(getString(R.string.team_title));
                    return true;
                }
                if (itemId == R.id.nav_settings) {

                    topAppBar.setTitle(getString(R.string.settings_title));
                    return true;
                }
                return super.onOptionsItemSelected(item);
            });
        }
    }
}