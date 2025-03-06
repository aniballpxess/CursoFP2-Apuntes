package edu.dam2.psp.eva2.ejercicios.sincronizacion.pruebasincro;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

public class HiloA extends Thread
{
    private Contador contador;

    public HiloA(String n, Contador c)
    {
        setName(n);
        contador = c;
    }

    public void run()
    {
        for (int j = 0; j < 300; j++)
        {
            contador.incrementa();
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
