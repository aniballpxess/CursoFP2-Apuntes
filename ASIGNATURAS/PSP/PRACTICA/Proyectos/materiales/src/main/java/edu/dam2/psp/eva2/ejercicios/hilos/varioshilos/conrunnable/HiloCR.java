package edu.dam2.psp.eva2.ejercicios.hilos.varioshilos.conrunnable;

public class HiloCR implements Runnable
{
    private int c;

    public HiloCR(int c)
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
