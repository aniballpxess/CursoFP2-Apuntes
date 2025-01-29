package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestionalmacen;

public class Almacen
{
    private int productos;
    private boolean puedePasar = true;

    public Almacen(int productos)
    {
        this.productos = productos;
    }

    public synchronized boolean sePuedePasar()
    {
        if (puedePasar)
        {
            puedePasar = false;
            return true;
        }
        else
        {
            return false;
        }
    }

    public synchronized void salirDelAlmacen()
    {
        puedePasar = true;
    }

    public boolean recogerProducto()
    {
        if (productos > 0)
        {
            productos--;
            return true;
        }
        return false;
    }
}
