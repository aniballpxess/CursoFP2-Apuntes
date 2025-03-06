package edu.dam2.psp.eva2.ejercicios.sincronizacion.pruebasincro;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class HiloB extends Thread
{
    private Contador contador;

    public HiloB(String n, Contador c)
    {
        setName(n);
        contador = c;
    }

    public void run()
    {
        for (int j = 0; j < 300; j++)
        {
            contador.decrementa();
            try
            {
                sleep(10);
            }
            catch (InterruptedException e)
            {
                for (StackTraceElement element : e.getStackTrace())
                {
                    printErrorLine(element.toString());
                }
            }
        }
        System.out.println(getName() + " contador vale " + contador.getValor());
    }
}
