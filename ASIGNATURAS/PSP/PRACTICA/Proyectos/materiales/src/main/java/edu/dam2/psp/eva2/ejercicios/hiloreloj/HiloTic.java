package edu.dam2.psp.eva2.ejercicios.hiloreloj;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class HiloTic extends Thread
{
    @Override
    public void run()
    {
        try
        {
            while (true) 
            {
                System.out.println("TIC");
                Thread.sleep(2000);
            }
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
