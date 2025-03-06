package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestionalmacen;

public class Almacen
{
    private int productos;
    private Entrada entrada;

    public Almacen(int productos)
    {
        this.productos = productos;
        this.entrada = new Entrada();
    }

    public Entrada getEntrada()
    {
        return entrada;
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