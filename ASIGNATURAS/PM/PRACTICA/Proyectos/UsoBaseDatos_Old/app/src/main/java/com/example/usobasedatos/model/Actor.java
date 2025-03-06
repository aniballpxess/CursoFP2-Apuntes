package com.example.usobasedatos.model;

public class Actor {
    private int id;          // Identificador único del actor
    private String nombre;   // Nombre del actor

    // Constructor vacío (necesario para ciertas operaciones, como trabajar con frameworks o bases de datos)
    public Actor() {}

    // Constructor con parámetros
    public Actor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Sobrescritura del método toString para representar al actor (útil en listas o logs)
    @Override
    public String toString() {
        return nombre;  // Devuelve solo el nombre para facilitar la visualización en adaptadores de lista
    }
}
