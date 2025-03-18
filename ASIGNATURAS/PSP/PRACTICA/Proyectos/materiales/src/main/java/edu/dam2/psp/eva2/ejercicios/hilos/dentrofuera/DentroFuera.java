package edu.dam2.psp.eva2.ejercicios.hilos.dentrofuera;

public class DentroFuera
{
    public static void main(String[] args)
    {
        Thread hiloFor = new HiloFor();
        hiloFor.start();
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Estoy fuera del hilo.");
        }
    }
}
