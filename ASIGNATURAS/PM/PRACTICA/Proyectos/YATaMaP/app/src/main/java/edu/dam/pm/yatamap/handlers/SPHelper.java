package edu.dam.pm.yatamap.handlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

public class SPHelper {
    private static final String PREF_NAME = "app_prefs";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_NIGHT_MODE = "night_mode";

    private SharedPreferences sharedPreferences;
    private Context context;

    public SPHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.context = context;
    }

    public boolean isUserIdSet() {
        return sharedPreferences.contains(KEY_USER_ID);
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, userId);
        editor.apply();
    }

    private boolean isNightModeSet() {
        return sharedPreferences.contains(KEY_NIGHT_MODE);
    }

    private boolean isNightModeEnabled() {
        return sharedPreferences.getBoolean(KEY_NIGHT_MODE, false);
    }

    private void saveNightMode(boolean enabled) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_NIGHT_MODE, enabled);
        editor.apply();
    }

    private boolean isSystemNightModeEnabled() {
        int uiMode_current = context.getResources().getConfiguration().uiMode;
        int nightMode_current = uiMode_current & Configuration.UI_MODE_NIGHT_MASK;
        return nightMode_current == Configuration.UI_MODE_NIGHT_YES;
    }

    // Should be called whenever an instance of SPHelper is created before using
    // any NightMode related functions
    public void setupDefaultNightMode() {
        if (isNightModeSet()) {
            int nightMode = AppCompatDelegate.MODE_NIGHT_NO;
            if (isNightModeEnabled()) {
                nightMode = AppCompatDelegate.MODE_NIGHT_YES;
            }
            AppCompatDelegate.setDefaultNightMode(nightMode);
        }
        else {
            saveNightMode(isSystemNightModeEnabled());
        }
    }

    public void changeNightMode() {
        if (!isNightModeSet()) {
            saveNightMode(isSystemNightModeEnabled());
        }
        int newNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        if (isNightModeEnabled()) {
            newNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        }
        AppCompatDelegate.setDefaultNightMode(newNightMode);
        saveNightMode(!isNightModeEnabled());
    }
}
