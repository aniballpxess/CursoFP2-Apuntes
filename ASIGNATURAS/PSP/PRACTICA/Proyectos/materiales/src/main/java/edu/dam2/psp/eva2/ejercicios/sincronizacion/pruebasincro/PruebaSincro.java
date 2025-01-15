package edu.dam2.psp.eva2.ejercicios.sincronizacion.pruebasincro;

public class PruebaSincro
{
    public static void main(String[] args)
    {
        Contador contador = new Contador(100);
        HiloA hiloA = new HiloA("Hilo A", contador);
        HiloB hiloB = new HiloB("Hilo B", contador);
        hiloA.start();
        hiloB.start();
    }
}
