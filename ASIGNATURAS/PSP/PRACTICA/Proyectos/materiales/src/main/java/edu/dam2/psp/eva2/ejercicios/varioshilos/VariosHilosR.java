package edu.dam2.psp.eva2.ejercicios.varioshilos;

public class VariosHilosR implements Runnable
{
    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            HiloC hiloC = new HiloC(i);
            hiloC.run();
        }
    }
    public static void main(String[] args)
    {
        VariosHilosR vhr = new VariosHilosR();
        vhr.run();
    }
}
