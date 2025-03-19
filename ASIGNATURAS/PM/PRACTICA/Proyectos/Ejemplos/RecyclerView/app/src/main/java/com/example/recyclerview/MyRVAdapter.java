package com.example.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRVAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Item> listaDeItems;

    public MyRVAdapter(List<Item> listaDeItems) {
        this.listaDeItems = listaDeItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item itemActual = listaDeItems.get(position);

        holder.tituloTextView.setText(itemActual.getTitulo());
        holder.imagenImageView.setImageResource(itemActual.getImagenResId());

        // Un onclick corto
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Has pulsado corto: " + itemActual.getTitulo(), Toast.LENGTH_SHORT).show();
        });

        // Un onclick largo que nos envía a Google
        holder.itemView.setOnLongClickListener(v -> {
            Toast.makeText(v.getContext(), "Has pulsado largo: " + itemActual.getTitulo(), Toast.LENGTH_SHORT).show();
            Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q="));
            v.getContext().startActivity(i1);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaDeItems.size();
    }
}
