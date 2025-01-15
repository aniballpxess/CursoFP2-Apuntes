package edu.dam2.psp.eva2.ejercicios.sincronizacion.pruebasincro;

class Contador
{
    private int c = 0;

    Contador(int c)
    {
        this.c = c;
    }

    synchronized public void incrementa()
    {
        c = c + 1;
    }

    synchronized public void decrementa()
    {
        c = c - 1;
    }

    synchronized public int getValor()
    {
        return c;
    }
}
