package edu.dam.pm.yatamap;

import android.os.Bundle;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        activityTitle = "Settings";
        setToolbarTitle(activityTitle);
    }
}