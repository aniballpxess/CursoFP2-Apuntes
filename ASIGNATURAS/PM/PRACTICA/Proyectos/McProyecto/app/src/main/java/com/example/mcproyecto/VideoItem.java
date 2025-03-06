package com.example.mcproyecto;

public class VideoItem {
    private final String title;
    private final String videoPath;

    public VideoItem(String title, String videoPath) {
        this.title = title;
        this.videoPath = videoPath;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoPath() {
        return videoPath;
    }
}
