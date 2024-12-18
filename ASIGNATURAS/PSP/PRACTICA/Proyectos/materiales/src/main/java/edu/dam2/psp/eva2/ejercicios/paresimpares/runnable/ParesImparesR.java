package edu.dam2.psp.eva2.ejercicios.paresimpares.runnable;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class ParesImparesR {
    public static void main(String[] args)
    {
        int limiteInf = 1;
        int limiteSup = 10;
        int tiempoEspera = 100;

        Runnable tareaPares = new HiloParesR(limiteInf, limiteSup, tiempoEspera);
        Runnable tareaImpares = new HiloImparesR(limiteInf, limiteSup, tiempoEspera);

        Thread contadorPares = new Thread(tareaPares);
        Thread contadorImpares = new Thread(tareaImpares);

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
