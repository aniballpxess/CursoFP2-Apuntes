package pm.pruebas.conector_a_bbdd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BaseActivity extends AppCompatActivity {


    private static final String PREFS_NAME = "theme_prefs";
    private static final String THEME_KEY = "isNightMode";

    private FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        super.setContentView(R.layout.activity_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.base), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isNightMode = preferences.getBoolean(THEME_KEY, false);
        if (isNightMode)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        contentFrame = findViewById(R.id.contentFrame);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (contentFrame != null) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View contentView = inflater.inflate(layoutResID, null);
            contentFrame.addView(contentView);
        }
    }
}