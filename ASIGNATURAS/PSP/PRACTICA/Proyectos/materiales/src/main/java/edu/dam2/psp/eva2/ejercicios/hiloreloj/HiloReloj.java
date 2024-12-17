package edu.dam2.psp.eva2.ejercicios.hiloreloj;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class HiloReloj
{
    public static void main(String[] args)
    {
        Thread hiloTic = new HiloTic();
        Thread hiloTac = new HiloTac();
        try
        {
            hiloTic.start();
            Thread.sleep(1000);
            hiloTac.start();
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
