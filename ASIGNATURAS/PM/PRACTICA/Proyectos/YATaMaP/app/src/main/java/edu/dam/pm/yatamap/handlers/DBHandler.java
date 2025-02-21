package edu.dam.pm.yatamap.handlers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.dam.pm.yatamap.classes.Task;
import edu.dam.pm.yatamap.classes.Team;
import edu.dam.pm.yatamap.classes.User;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task_manager.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_TASKS = "tasks";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_TEAMS = "teams";
    private static final String TABLE_TASK_TYPES = "task_types";

    // Task fields
    private static final String KEY_TASK_ID = "id";
    private static final String KEY_TASK_NAME = "name";
    private static final String KEY_TASK_OWNER_ID = "owner_id"; // FK to User
    private static final String KEY_TASK_DESCRIPTION = "description";
    private static final String KEY_TASK_TYPE_ID = "task_type_id"; // FK to TaskType
    private static final String KEY_TASK_PRIORITY = "priority";
    private static final String KEY_TASK_DONE = "done";
    private static final String KEY_TASK_DATE = "scheduled_date";

    // User fields
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_TEAM_ID = "team_id"; // FK to Team

    // Team fields
    private static final String KEY_TEAM_ID = "id";
    private static final String KEY_TEAM_NAME = "name";

    // TaskType fields
    private static final String KEY_TASK_TYPE_ID_PK = "id";
    private static final String KEY_TASK_TYPE_NAME = "name";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create TaskTypes table
        String CREATE_TASK_TYPES_TABLE = "CREATE TABLE " + TABLE_TASK_TYPES + "("
                + KEY_TASK_TYPE_ID_PK + " TEXT PRIMARY KEY,"
                + KEY_TASK_TYPE_NAME + " TEXT)";

        // Create Teams table first (parent of Users)
        String CREATE_TEAMS_TABLE = "CREATE TABLE " + TABLE_TEAMS + "("
                + KEY_TEAM_ID + " TEXT PRIMARY KEY,"
                + KEY_TEAM_NAME + " TEXT)";

        // Create Users table (parent of Tasks, child of Teams)
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USER_ID + " TEXT PRIMARY KEY,"
                + KEY_USER_NAME + " TEXT,"
                + KEY_USER_TEAM_ID + " TEXT,"
                + "FOREIGN KEY(" + KEY_USER_TEAM_ID + ") REFERENCES " + TABLE_TEAMS + "(" + KEY_TEAM_ID + ") ON DELETE CASCADE)";

        // Create Tasks table (child of Users & TaskTypes)
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_TASK_ID + " TEXT PRIMARY KEY,"
                + KEY_TASK_NAME + " TEXT,"
                + KEY_TASK_OWNER_ID + " TEXT,"
                + KEY_TASK_DESCRIPTION + " TEXT,"
                + KEY_TASK_TYPE_ID + " TEXT,"
                + KEY_TASK_PRIORITY + " INTEGER,"
                + KEY_TASK_DONE + " INTEGER,"
                + KEY_TASK_DATE + " INTEGER,"
                + "FOREIGN KEY(" + KEY_TASK_OWNER_ID + ") REFERENCES " + TABLE_USERS + "(" + KEY_USER_ID + ") ON DELETE CASCADE,"
                + "FOREIGN KEY(" + KEY_TASK_TYPE_ID + ") REFERENCES " + TABLE_TASK_TYPES + "(" + KEY_TASK_TYPE_ID_PK + "))";

        // Execute Queries
        db.execSQL(CREATE_TASK_TYPES_TABLE);
        db.execSQL(CREATE_TEAMS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK_TYPES);
        onCreate(db);
    }

    public User getUserById(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        List<Task> taskList;

        String userQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER_ID + " = ?";
        Cursor userCursor = db.rawQuery(userQuery, new String[]{userId});

        if (userCursor != null && userCursor.moveToFirst()) {
            String id = userCursor.getString(userCursor.getColumnIndexOrThrow(KEY_USER_ID));
            String name = userCursor.getString(userCursor.getColumnIndexOrThrow(KEY_USER_NAME));

            user = new User(id, name);
        }

        if (userCursor != null) {
            userCursor.close();
        }

        assert user != null;
        taskList = getTasksByUserId(userId);
        user.setTasks(taskList);

        return user;
    }

    public Team getTeamById(String teamId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Team team = null;
        List<User> membersList = new ArrayList<>();

        // Fetch Team
        String teamQuery = "SELECT * FROM " + TABLE_TEAMS + " WHERE " + KEY_TEAM_ID + " = ?";
        Cursor teamCursor = db.rawQuery(teamQuery, new String[]{teamId});

        if (teamCursor != null && teamCursor.moveToFirst()) {
            String id = teamCursor.getString(teamCursor.getColumnIndexOrThrow(KEY_TEAM_ID));
            String name = teamCursor.getString(teamCursor.getColumnIndexOrThrow(KEY_TEAM_NAME));

            team = new Team(id, name, membersList);
        }

        if (teamCursor != null) {
            teamCursor.close();
        }

        // Fetch Users Belonging to the Team
        String userQuery = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USER_TEAM_ID + " = ?";
        Cursor userCursor = db.rawQuery(userQuery, new String[]{teamId});

        if (userCursor != null && userCursor.moveToFirst()) {
            do {
                String userId = userCursor.getString(userCursor.getColumnIndexOrThrow(KEY_USER_ID));
                String userName = userCursor.getString(userCursor.getColumnIndexOrThrow(KEY_USER_NAME));

                User user = new User(userId, userName);
                membersList.add(user);
            } while (userCursor.moveToNext());
        }

        if (userCursor != null) {
            userCursor.close();
        }

        db.close();
        return team;
    }

    public List<Task> getTasksByUserId(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Task> taskList = new ArrayList<>();

        String taskQuery = "SELECT * FROM " + TABLE_TASKS + " WHERE " + KEY_TASK_OWNER_ID + " = ?";
        Cursor cursor = db.rawQuery(taskQuery, new String[]{userId});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String taskId = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TASK_ID));
                String taskName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TASK_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(KEY_TASK_DESCRIPTION));
                int priority = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TASK_PRIORITY));
                boolean done = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TASK_DONE)) == 1;
                long scheduledDateMillis = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_TASK_DATE));

                // Create Task object
                Task task = new Task(taskId, taskName, description, null, priority, done, new Date(scheduledDateMillis));
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return taskList;
    }

}
