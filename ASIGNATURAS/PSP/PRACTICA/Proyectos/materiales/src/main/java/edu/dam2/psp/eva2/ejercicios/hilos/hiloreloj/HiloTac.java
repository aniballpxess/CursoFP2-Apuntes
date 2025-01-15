package edu.dam2.psp.eva2.ejercicios.hilos.hiloreloj;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class HiloTac extends Thread
{
    @Override
    public void run()
    {
        try
        {
            while (true) 
            {
                System.out.println("TAC");
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
