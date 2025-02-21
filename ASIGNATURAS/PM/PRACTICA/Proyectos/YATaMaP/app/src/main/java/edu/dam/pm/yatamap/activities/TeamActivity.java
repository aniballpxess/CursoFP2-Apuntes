package edu.dam.pm.yatamap.activities;

import android.os.Bundle;

import edu.dam.pm.yatamap.R;

public class TeamActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        setToolbarTitle(getString(R.string.team_title));
    }
}