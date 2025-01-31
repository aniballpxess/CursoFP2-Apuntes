package com.example.usobasedatos.model;

// Clase Pelicula que representa el modelo de datos de una película
// Añadiremos el uso de los atributos que no utilizamos
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

    // Sobrescribimos el método toString() para mostrar la información de la película
    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
                "Director: " + director;
    }
}

