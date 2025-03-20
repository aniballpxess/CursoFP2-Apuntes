package edu.dam.pm.yatamap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import edu.dam.pm.yatamap.R;
import edu.dam.pm.yatamap.classes.Task;
import edu.dam.pm.yatamap.classes.TaskType;
import edu.dam.pm.yatamap.classes.Team;
import edu.dam.pm.yatamap.classes.User;
import edu.dam.pm.yatamap.databinding.ActivityMainBinding;
import edu.dam.pm.yatamap.helpers.DBHelper;
import edu.dam.pm.yatamap.helpers.SPHelper;
import edu.dam.pm.yatamap.views.main.home.HomeFragment;
import edu.dam.pm.yatamap.views.main.settings.SettingsFragment;
import edu.dam.pm.yatamap.views.main.tasks.TasksFragment;
import edu.dam.pm.yatamap.views.main.team.TeamFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentContainerView fragmentContainer;
    protected MaterialToolbar topAppBar;
    protected BottomNavigationView bottomNav;
    protected SPHelper spHelper;
    protected DBHelper dbHelper;

    // Lists to store data
    private List<Task> tasks;
    private List<TaskType> taskTypes;
    private List<User> users;

    // Objects to store current user and team
    private User currentUser;
    private Team currentTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("BASE", "Load Start");

        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentContainer = binding.fragmentContainer;
        topAppBar = binding.topToolbar;
        bottomNav = binding.bottomNavigation;
        spHelper = new SPHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.base, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                            | WindowInsetsCompat.Type.displayCutout()
            );
           v.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        spHelper.setupDefaultNightMode();
        setSupportActionBar(topAppBar);
        topAppBar.setTitle(getString(R.string.home_title));
        setupBottomNavigation();

        loadDataAndSetupUser();

        Log.d("BASE", "Load Finish");
    }

    // TODO - Check this CHATGPT BullShit
    private void loadDataAndSetupUser() {
        String userId = spHelper.getUserId();
        if (userId == null) {
            UserSetup();
        } else {
            currentUser = dbHelper.getUserById(userId);
            if (currentUser != null) {
                currentTeam = dbHelper.getTeamById(currentUser.getTeamId());
                tasks = dbHelper.getAllTasks();
                taskTypes = dbHelper.getAllTaskTypes();
                users = dbHelper.getAllUsers();
            } else {
                UserSetup();
            }
        }
    }

    // TODO - Check this CHATGPT BullShit
    private void UserSetup() {
        Team newTeam = new Team("New Team");
        long teamId = dbHelper.addTeam(newTeam);
        newTeam.setId(teamId);
        User newUser = new User("New User", teamId);
        long userId = dbHelper.addUser(newUser);
        newUser.setId(userId);
        spHelper.setUserId(userId);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_night_mode) {
            spHelper.changeNightMode();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setupBottomNavigation() {
        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                loadFragment(new HomeFragment());
                topAppBar.setTitle(getString(R.string.home_title));
                return true;
            }
            if (itemId == R.id.nav_tasks) {
                loadFragment(new TasksFragment());
                topAppBar.setTitle(getString(R.string.tasks_title));
                return true;
            }
            if (itemId == R.id.nav_team) {
                loadFragment(new TeamFragment());
                topAppBar.setTitle(getString(R.string.team_title));
                return true;
            }
            if (itemId == R.id.nav_settings) {
                loadFragment(new SettingsFragment());
                topAppBar.setTitle(getString(R.string.settings_title));
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void loadFragment(Fragment fragment) {
        // Replace the fragment in the container
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
