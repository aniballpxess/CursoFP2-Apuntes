package com.example.mcproyecto;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class BadActivity extends AppCompatActivity {

    private MediaPlayer sonido;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            finish(); // Exit the activity
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad);

        sonido = MediaPlayer.create(this, R.raw.sad_music);
        sonido.start();
        sonido.setLooping(true);

        // Schedule the activity to finish after 4 seconds
        handler.postDelayed(runnable, 4000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (sonido != null) {
            sonido.stop();
            sonido.release();
        }

        // Remove the callback to avoid memory leaks
        handler.removeCallbacks(runnable);
    }
}