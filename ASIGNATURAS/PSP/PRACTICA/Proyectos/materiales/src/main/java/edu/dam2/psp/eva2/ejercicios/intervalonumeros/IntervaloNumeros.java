package edu.dam2.psp.eva2.ejercicios.intervalonumeros;

import static edu.dam2.psp.utiles.Funciones.leerEntrada;

import java.util.Scanner;

public class IntervaloNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int limiteInf;
        int limiteSup;

        System.out.println("Introduce el intervalo de numeros a representar.");
        limiteInf = Integer.parseInt(leerEntrada(sc, "Limite Inferior: "));
        limiteSup = Integer.parseInt(leerEntrada(sc, "Limite Superior: "));

        Runnable tareaMostrarIntervalo = new MostrarIntervalo(limiteInf, limiteSup);
        Thread hiloEjecucion = new Thread(tareaMostrarIntervalo);
        hiloEjecucion.start();
    }
}
