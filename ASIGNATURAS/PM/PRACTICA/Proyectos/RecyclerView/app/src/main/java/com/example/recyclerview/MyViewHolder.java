package com.example.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tituloTextView;
    ImageView imagenImageView;

    public MyViewHolder(View itemView) {
        super(itemView);
        tituloTextView = itemView.findViewById(R.id.tituloTextView);
        imagenImageView = itemView.findViewById(R.id.imagenImageView);
    }
}
