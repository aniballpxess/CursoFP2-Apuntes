package com.example.mcproyecto;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Bola2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bola2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.videoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<VideoItem> videoList = new ArrayList<>();
        videoList.add(new VideoItem("Ande, ande, ande", "android.resource://" + getPackageName() + "/" + R.raw.andeandeande));
        videoList.add(new VideoItem("Arre Borriquito", "android.resource://" + getPackageName() + "/" + R.raw.arreborriquito));
        videoList.add(new VideoItem("Blanca Navidad", "android.resource://" + getPackageName() + "/" + R.raw.blancanavidad));
        videoList.add(new VideoItem("Campana Sobre Campana", "android.resource://" + getPackageName() + "/" + R.raw.campanasobrecampana));

        VideoAdapter adapter = new VideoAdapter(this, videoList);
        recyclerView.setAdapter(adapter);
    }
}
