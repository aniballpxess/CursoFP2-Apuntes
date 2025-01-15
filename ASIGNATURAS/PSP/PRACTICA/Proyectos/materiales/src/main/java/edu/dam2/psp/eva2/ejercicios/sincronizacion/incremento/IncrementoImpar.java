package edu.dam2.psp.eva2.ejercicios.sincronizacion.incremento;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class IncrementoImpar implements Runnable
{
    private Contador contador;
    private final int limite;

    public IncrementoImpar(Contador contador)
    {
        this.contador = contador;
        this.limite = contador.getLimite();
    }

    @Override
    public void run()
    {
        String threadName = Thread.currentThread().getName();
        while (true)
        {
            int valorContador = contador.getValor();
            if (valorContador == limite)
            {
                break;
            }
            if (valorContador % 2 != 0)
            {
                contador.incrementa();
                System.out.println(threadName + " incrementa el contador a " + valorContador);
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    for (StackTraceElement element : e.getStackTrace())
                    {
                        printErrorLine(element.toString());
                    }
                }
            }
            else {
                System.out.println(threadName + " INTENTA incrementar el contador.");
            }
        }
    }

}
