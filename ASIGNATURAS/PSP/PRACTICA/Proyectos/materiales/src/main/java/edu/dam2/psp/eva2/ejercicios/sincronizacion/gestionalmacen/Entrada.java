package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestionalmacen;

public class Entrada
{
    private boolean abierta;

    public Entrada()
    {
        abierta = true;
    }

    public synchronized boolean sePuedePasar()
    {
        if (abierta)
        {
            abierta = false;
            return true;
        }
        return false;
    }

    public synchronized void salir()
    {
        abierta = false;
    }
}
