package com.example.mcproyecto;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BolaEstrella extends AppCompatActivity {

    private ImageView starImage;
    private ImageView targetImage; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bola_estrella);

        starImage = findViewById(R.id.star_image);
        targetImage = findViewById(R.id.target_image); // Add this line

        starImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }

    private void startAnimation() {
        int[] targetLocation = new int[2];
        targetImage.getLocationOnScreen(targetLocation);

        TranslateAnimation animation = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, targetLocation[0] - starImage.getLeft(),
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, targetLocation[1] - starImage.getTop()
        );
        animation.setDuration(2000);
        animation.setFillAfter(true);
        starImage.startAnimation(animation);
    }
}