package edu.dam2.psp.eva2.ejercicios.varioshilos.original;

public class HiloC extends Thread
{
    private int c;

    public HiloC(int c)
    {
        this.c = c;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println("Hilo " + c + " línea " + i + ".");
        }
    }
}
