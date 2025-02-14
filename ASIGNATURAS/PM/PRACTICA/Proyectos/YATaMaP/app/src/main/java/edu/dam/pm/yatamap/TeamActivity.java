package edu.dam.pm.yatamap;

import android.os.Bundle;

public class TeamActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        setupBottomNavigation(R.id.nav_team);
    }
}