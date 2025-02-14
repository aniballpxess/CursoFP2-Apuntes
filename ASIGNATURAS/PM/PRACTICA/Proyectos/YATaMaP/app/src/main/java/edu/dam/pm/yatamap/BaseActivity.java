package edu.dam.pm.yatamap;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.dam.pm.yatamap.handlers.SPHelper;

public class BaseActivity extends AppCompatActivity {

    private SPHelper spHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        super.setContentView(R.layout.activity_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.base), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spHelper = new SPHelper(this);

        if (!spHelper.isUserSet()) {
            startActivity(new Intent(this, UserSetupActivity.class));
            finish();
            return;
        }

        AppCompatDelegate.setDefaultNightMode( spHelper.isNightModeEnabled() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO );

        loadActivityContent(getActivityLayoutResId());
    }

    protected void loadActivityContent(int layoutResId) {
        // Find the FrameLayout and inject the child content
        View contentFrame = findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResId, (ViewGroup) contentFrame);
    }

    protected int getActivityLayoutResId() {
        // Return the layout resource ID for the specific activity
        return R.layout.activity_home;  // Change dynamically for each activity
    }

//    @Override
//    public void setContentView(int layoutResID) {
//        if (contentFrame != null) {
//            LayoutInflater inflater = LayoutInflater.from(this);
//            View contentView = inflater.inflate(layoutResID, null);
//            contentFrame.addView(contentView);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);

        MenuItem nightModeItem = menu.findItem(R.id.action_night_mode);

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

    protected void setupBottomNavigation(int selectedItemId) {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(selectedItemId);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (itemId == R.id.nav_tasks) {
                startActivity(new Intent(this, TasksActivity.class));
            } else if (itemId == R.id.nav_team) {
                startActivity(new Intent(this, TeamActivity.class));
            } else if (itemId == R.id.nav_settings) {
                startActivity(new Intent(this, SettingsActivity.class));
            }
            finish();
            return true;
        });
    }
}