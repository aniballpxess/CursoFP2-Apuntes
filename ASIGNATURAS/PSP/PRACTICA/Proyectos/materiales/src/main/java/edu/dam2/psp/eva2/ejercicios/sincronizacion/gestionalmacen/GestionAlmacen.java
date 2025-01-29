package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestionalmacen;

public class GestionAlmacen
{
    public static void main(String[] args)
    {
        Almacen almacen = new Almacen(100);
        for (int i = 0; i < 300; i++)
        {
            Runnable tarea_cliente = new Cliente(almacen, 10, "Cliente Nº " + i);
            Thread hilo_cliente = new Thread(tarea_cliente);
            hilo_cliente.start();
        }
    }
}
