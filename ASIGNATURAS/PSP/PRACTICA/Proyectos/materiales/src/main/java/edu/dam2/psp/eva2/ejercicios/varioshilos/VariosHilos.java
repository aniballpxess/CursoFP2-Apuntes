package edu.dam2.psp.eva2.ejercicios.varioshilos;

public class VariosHilos
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 5; i++)
        {
            Thread hiloC = new HiloC(i);
            hiloC.start();
        }
    }
}
