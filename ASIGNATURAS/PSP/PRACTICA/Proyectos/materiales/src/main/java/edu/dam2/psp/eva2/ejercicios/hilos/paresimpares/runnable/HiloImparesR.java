package edu.dam2.psp.eva2.ejercicios.hilos.paresimpares.runnable;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class HiloImparesR implements Runnable
{
    private final int limiteInf;
    private final int limiteSup;
    private final int tiempoEspera;

    public HiloImparesR(int limiteInf, int limiteSup, int tiempoEspera)
    {
        this.limiteInf = limiteInf;
        this.limiteSup = limiteSup;
        this.tiempoEspera = tiempoEspera;
    }

    @Override
    public void run()
    {
        for (int i = limiteInf; i <= limiteSup; i++)
        {
            if (i % 2 != 0)
            {
                System.out.println("Impar: " + i);
            } 
            else 
            {
                try
                {
                    Thread.sleep(tiempoEspera);
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
}
