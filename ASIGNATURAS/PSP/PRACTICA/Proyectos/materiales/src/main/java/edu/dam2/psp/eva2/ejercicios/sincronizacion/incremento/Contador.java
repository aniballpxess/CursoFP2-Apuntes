package edu.dam2.psp.eva2.ejercicios.sincronizacion.incremento;

public class Contador
{
    private int c = 0;
    private final int limite;

    public Contador(int limite)
    {
        this.limite = limite;
    }

    public Contador()
    {
        this.limite = Integer.MAX_VALUE;
    }

    public int getLimite()
    {
        return limite;
    }

    synchronized public int getValor()
    {
        return c;
    }

    synchronized public void incrementa()
    {
        c = c + 1;
    }
}
