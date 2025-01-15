package edu.dam2.psp.eva2.ejercicios.hilos.varioshilos.pruebajoin;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class PruebaJoin
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 5; i++)
        {
            try
            {
                Thread hiloJoin = new HiloJoin(i);
                hiloJoin.start();
                hiloJoin.join();
            }
            catch (InterruptedException e)
            {
                for (StackTraceElement element : e.getStackTrace())
                {
                    printErrorLine(element.toString());
                }
            }
        }
    }

}
