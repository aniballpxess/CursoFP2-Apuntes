package edu.dam2.psp.eva2.ejercicios.intervalonumeros;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class MostrarIntervalo implements Runnable {
    private final int limiteInf;
    private final int limiteSup;

    public MostrarIntervalo(int limiteInf, int limiteSup)
    {
        this.limiteInf = limiteInf;
        this.limiteSup = limiteSup;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(1500);
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
