package edu.dam2.psp.eva2.ejercicios.sincronizacion.incremento;

public class Incremento {
    public static void main(String[] args) {
        Contador contador = new Contador(20);
        Runnable tarea_incPar = new IncrementoPar(contador);
        Runnable tarea_incImpar = new IncrementoImpar(contador);

        Thread contadorPares = new Thread(tarea_incPar, "Hilo_Incremento_Par");
        Thread contadorImpares = new Thread(tarea_incImpar, "Hilo_Incremento_Impar");

        contadorPares.start();
        contadorImpares.start();

        try
        {
            contadorPares.join();
            contadorImpares.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        contador.printLogs();
    }
}
