package edu.dam.pm.yatamap.activities;

import android.os.Bundle;

import edu.dam.pm.yatamap.R;

public class TasksActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        setToolbarTitle(getString(R.string.tasks_title));
    }
}