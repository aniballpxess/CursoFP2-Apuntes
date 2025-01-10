package com.example.usobasedatos;

import androidx.annotation.NonNull;

public class Pelicula {
    private final int id;
    private final String titulo;
    private final String director;
    private final int cartel;
    private final int musica;

    public Pelicula(int id, String titulo, String director, int cartel, int musica) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.cartel = cartel;
        this.musica = musica;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    @NonNull
    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
                "Director: " + director;
    }
}