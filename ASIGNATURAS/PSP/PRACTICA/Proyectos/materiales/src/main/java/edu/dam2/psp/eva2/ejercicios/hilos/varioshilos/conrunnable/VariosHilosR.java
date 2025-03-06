package edu.dam2.psp.eva2.ejercicios.hilos.varioshilos.conrunnable;

public class VariosHilosR implements Runnable
{
    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            HiloCR hcr = new HiloCR(i);
            hcr.run();
        }
    }
    public static void main(String[] args)
    {
        VariosHilosR vhr = new VariosHilosR();
        vhr.run();
    }
}
