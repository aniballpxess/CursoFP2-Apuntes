package edu.dam.pm.yatamap;

import android.os.Bundle;

public class TasksActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        activityTitle = "Tasks";
        setToolbarTitle(activityTitle);
    }
}