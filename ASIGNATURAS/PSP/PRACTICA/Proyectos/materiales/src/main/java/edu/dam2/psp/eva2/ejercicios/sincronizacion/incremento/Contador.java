package edu.dam2.psp.eva2.ejercicios.sincronizacion.incremento;

import java.util.ArrayList;
import java.util.List;

public class Contador
{
    private int c = 0;
    private final int limite;
    private final List<String> logs;

    public Contador(int limite)
    {
        this.limite = limite;
        this.logs = new ArrayList<>();
    }

    public Contador()
    {
        this.limite = Integer.MAX_VALUE;
        this.logs = new ArrayList<>();
    }

    public int getLimite()
    {
        return limite;
    }

    public void printLogs()
    {
        for (String log : logs)
        {
            System.out.println(log);
        }
    }

    synchronized public int getValor(String threadName)
    {
        logs.add(threadName + " INTENTA incrementar el contador.");
        return c;
    }

    synchronized public void incrementa(String threadName)
    {
        c = c + 1;
        logs.add(threadName + " incrementar el contador a " + c + ".");
    }
}
