package edu.dam.pm.yatamap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.dam.pm.yatamap.classes.Team;
import edu.dam.pm.yatamap.classes.User;
import edu.dam.pm.yatamap.handlers.DBHandler;
import edu.dam.pm.yatamap.handlers.SPHelper;

public class UserSetupActivity extends AppCompatActivity {
    private EditText etUserName;
    private EditText etTeamName;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setup);

        etUserName = findViewById(R.id.etUserName);
        etTeamName = findViewById(R.id.etTeamName);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveUserAndTeam());
    }

    private void saveUserAndTeam() {
        String userName = etUserName.getText().toString().trim();
        String teamName = etTeamName.getText().toString().trim();

        if (userName.isEmpty() || teamName.isEmpty()) {
            Toast.makeText(this, "Please enter both your name and the name of your team.", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(userName);
        Team team = new Team(teamName);
        team.addMember(user);
        DBHandler db = new DBHandler(this);
        // TODO - save them to db

        SPHelper spHelper = new SPHelper(this);
        spHelper.saveUserId(user.getId());

        Intent intent = new Intent(UserSetupActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
