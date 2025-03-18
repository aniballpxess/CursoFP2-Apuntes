package edu.dam2.psp.eva2.ejercicios.clases.intervalostiempo;

public class Tiempo {
    private int segundosTotales;

    public Tiempo(int horas, int minutos, int segundos) {
        this.segundosTotales = (horas * 60 + minutos) * 60 + segundos;
    }

    public Tiempo(int segundos) {
        this.segundosTotales = segundos;
    }

    public Tiempo sumar(Tiempo t) {
        return new Tiempo(this.segundosTotales + t.segundosTotales);
    }

    public Tiempo restar(Tiempo t) {
        return new Tiempo(this.segundosTotales - t.segundosTotales);
    }

    @Override
    public String toString() {
        int segundos = segundosTotales % 60;
        int minutos = segundosTotales / 60;
        int horas = minutos / 60;
        minutos = minutos % 60;
        return horas + "h " + minutos + "m " + segundos + "s";
    }
}
