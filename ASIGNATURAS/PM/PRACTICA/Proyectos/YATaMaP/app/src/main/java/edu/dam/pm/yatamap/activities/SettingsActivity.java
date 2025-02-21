package edu.dam.pm.yatamap.activities;

import android.os.Bundle;

import edu.dam.pm.yatamap.R;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setToolbarTitle(getString(R.string.settings_title));
    }
}