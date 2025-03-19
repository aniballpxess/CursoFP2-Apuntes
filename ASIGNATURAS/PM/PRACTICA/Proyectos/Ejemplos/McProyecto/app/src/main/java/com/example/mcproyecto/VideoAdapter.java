package com.example.mcproyecto;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private final Context context;
    private final List<VideoItem> videoList;

    public VideoAdapter(Context context, List<VideoItem> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoItem videoItem = videoList.get(position);

        holder.title.setText(videoItem.getTitle());
        holder.videoView.setVideoPath(videoItem.getVideoPath());
        holder.videoView.seekTo(1); // Muestra el primer fotograma

        // Botón de reproducción/pausa
        holder.playPauseButton.setOnClickListener(v -> {
            if (holder.videoView.isPlaying()) {
                holder.videoView.pause();
                holder.playPauseButton.setText("Reproducir");
            } else {
                holder.videoView.start();
                holder.playPauseButton.setText("Pausar");
            }
        });

        // Restablece el botón al estado inicial cuando termina el video
        holder.videoView.setOnCompletionListener(mp -> holder.playPauseButton.setText("Reproducir"));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        VideoView videoView;
        Button playPauseButton;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.videoTitle);
            videoView = itemView.findViewById(R.id.videoView);
            playPauseButton = itemView.findViewById(R.id.playPauseButton);
        }
    }
}
