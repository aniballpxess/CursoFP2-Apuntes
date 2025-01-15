package edu.dam2.psp.eva2.ejercicios.sincronizacion.incremento;

public class Incremento {
    public static void main(String[] args) {
        Contador contador = new Contador(10);
        Runnable tarea_incPar = new IncrementoPar(contador);
        Runnable tarea_incImpar = new IncrementoImpar(contador);

        Thread contadorPares = new Thread(tarea_incPar, "Hilo incremento par");
        Thread contadorImpares = new Thread(tarea_incImpar, "Hilo incremento impar");

        contadorPares.start();
        contadorImpares.start();
    }
}
