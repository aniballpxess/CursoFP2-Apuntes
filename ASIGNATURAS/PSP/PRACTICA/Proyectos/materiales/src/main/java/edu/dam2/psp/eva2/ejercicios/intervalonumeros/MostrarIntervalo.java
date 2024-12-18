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
            int contador = 0;
            System.out.print("{\n\t");
            for (int i = limiteInf; i <= limiteSup; i++) {
                if (contador == 10) {
                    System.out.print("\n\t");
                }
                System.out.print("");
                Thread.sleep(1500);
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
