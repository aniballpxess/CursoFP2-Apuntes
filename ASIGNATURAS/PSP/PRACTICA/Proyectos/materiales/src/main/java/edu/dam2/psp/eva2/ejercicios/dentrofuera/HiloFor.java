package edu.dam2.psp.eva2.ejercicios.dentrofuera;

public class HiloFor extends Thread
{
    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Estoy dentro del hilo.");
        }
    }
}
