package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestioncontador;

public class GestionContador
{
    public static void main(String[] args)
    {
        Contador contador = new Contador(6, 10, 0, 100);
        Runnable tarea_produccion = new Productor(contador, 100);
        Runnable tarea_consumo = new Consumidor(contador, 200);
        Thread hilo_produccion = new Thread(tarea_produccion);
        Thread hilo_consumo = new Thread(tarea_consumo);

        System.out.println("Comienzo del programa.");
        hilo_produccion.start();
        hilo_consumo.start();
        try
        {
            hilo_produccion.join();
            hilo_consumo.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Fin del programa.");
    }
}
