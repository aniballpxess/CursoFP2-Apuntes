package com.example.recyclerview;

public class Item {
    private String titulo;
    private int imagenResId;

    public Item(String titulo, int imagenResId) {
        this.titulo = titulo;
        this.imagenResId = imagenResId;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
