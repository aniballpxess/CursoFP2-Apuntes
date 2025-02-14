package edu.dam.pm.yatamap.handlers;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;

public class SPHelper {
    private static final String PREF_NAME = "app_prefs";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_NIGHT_MODE = "night_mode";

    private SharedPreferences sharedPreferences;

    public SPHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // User check
    public boolean isUserSet() {
        return sharedPreferences.contains(KEY_USER_ID);
    }

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    // Night Mode
    public boolean isNightModeEnabled() {
        return sharedPreferences.getBoolean(KEY_NIGHT_MODE, false);
    }

    public void setNightMode(boolean enabled) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_NIGHT_MODE, enabled);
        editor.apply();

        AppCompatDelegate.setDefaultNightMode( enabled ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO );
    }
}
