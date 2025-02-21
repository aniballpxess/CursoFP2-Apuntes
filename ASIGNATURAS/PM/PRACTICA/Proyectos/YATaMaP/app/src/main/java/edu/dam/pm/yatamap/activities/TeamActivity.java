package edu.dam.pm.yatamap.activities;

import android.os.Bundle;
import android.util.Log;

import edu.dam.pm.yatamap.R;

public class TeamActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TEAM", "Load Start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        setToolbarTitle(getString(R.string.team_title));
        Log.d("TEAM", "Load Finish");
    }
}