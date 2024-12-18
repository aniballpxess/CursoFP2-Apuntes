package edu.dam2.psp.eva2.ejercicios.paresimpares.thread;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class ParesImparesT
{
    public static void main(String[] args)
    {
        int limiteInf = 1;
        int limiteSup = 10;
        int tiempoEspera = 100;

        Thread contadorPares = new HiloParesT(limiteInf, limiteSup, tiempoEspera);
        Thread contadorImpares = new HiloImparesT(limiteInf, limiteSup, tiempoEspera);

        try
        {
            contadorPares.start();
            Thread.sleep(tiempoEspera / 2);
            contadorImpares.start();
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
