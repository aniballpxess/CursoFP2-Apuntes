package edu.dam2.psp.eva2.ejercicios.sincronizacion.incremento;

import java.util.ArrayList;
import java.util.List;

public class Contador
{
    private int valor = 0;
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
        if (valor < limite) 
        {
            logs.add(threadName + " INTENTA incrementar el contador.");
        }
        return valor;
    }

    synchronized public void incrementa(String threadName)
    {
        valor = valor + 1;
        logs.add(threadName + " incrementa el contador a " + valor + ".");
    }
}
