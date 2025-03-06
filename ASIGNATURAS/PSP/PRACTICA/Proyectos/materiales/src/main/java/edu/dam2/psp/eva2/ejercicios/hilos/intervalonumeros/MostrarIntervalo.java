package edu.dam2.psp.eva2.ejercicios.hilos.intervalonumeros;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class MostrarIntervalo implements Runnable
{
    private final int limiteInf;
    private final int limiteSup;
    private final int numMaxSize;

    public MostrarIntervalo(int limiteInf, int limiteSup)
    {
        int digitos_LI = String.valueOf(limiteInf).length();
        int digitos_LS = String.valueOf(limiteSup).length();

        this.limiteInf = limiteInf;
        this.limiteSup = limiteSup;
        this.numMaxSize = Math.max(digitos_LI, digitos_LS);
    }

    @Override
    public void run()
    {
        try
        {
            int contador = 0;
            System.out.print("{\n  ");
            for (int i = limiteInf; i <= limiteSup; i++)
            {
                if (contador == 10)
                {
                    System.out.print("\n  ");
                    contador = 0;
                }
                System.out.printf("%" + numMaxSize + "d, ", i);
                Thread.sleep(1500);
                contador++;
            }
            System.out.print("\n}\n");
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
