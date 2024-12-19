package edu.dam2.psp.eva2.ejercicios.mesesestaciones;

import java.util.Scanner;

public class MesesEstaciones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce una estación para que te diga sus meses.");
        System.out.print("Estacion: ");
        String estacionUsuario = sc.nextLine();

        Runnable tareaEscogerMesesEstacion = new EscogerMesesEstacion(estacionUsuario);
        Thread hiloEscogerMesesEstacion = new Thread(tareaEscogerMesesEstacion);
        hiloEscogerMesesEstacion.start();

        sc.close();
    }
}
