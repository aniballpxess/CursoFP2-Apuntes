package edu.dam.pm.yatamap.activities;

import android.os.Bundle;
import android.util.Log;

import edu.dam.pm.yatamap.R;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("HOME", "Load Start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolbarTitle(getString(R.string.home_title));
        Log.d("HOME", "Load Finish");
    }
}