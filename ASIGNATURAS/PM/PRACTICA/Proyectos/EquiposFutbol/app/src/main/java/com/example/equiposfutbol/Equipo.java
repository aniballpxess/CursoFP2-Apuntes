package com.example.equiposfutbol;

public class Equipo {
    private String nombre;
    private int logo;

    public Equipo(String nombre, int logo) {
        this.nombre = nombre;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLogo() {
        return logo;
    }
}
