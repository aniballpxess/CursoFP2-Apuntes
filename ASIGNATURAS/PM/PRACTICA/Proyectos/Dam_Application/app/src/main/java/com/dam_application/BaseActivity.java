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

    // FrameLayout to allow derived activities to add their content
    private FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the base layout
        setContentView(R.layout.activity_base);

        // Setup the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find the content frame
        contentFrame = findViewById(R.id.contentFrame);
    }

    /**
     * Adds the content view of the derived activity into the content frame.
     * @param layoutResID Layout resource ID of the derived activity.
     */
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
        // Inflate a common menu (if any)
        getMenuInflater().inflate(R.menu.common_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle common menu item clicks
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Handle settings action
                return true;
            case R.id.action_help:
                // Handle help action
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
