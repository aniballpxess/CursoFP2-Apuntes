package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestioncontador;

public class Consumidor implements Runnable
{
    private Contador contador;
    private final int TIEMPO_CONSUMICION;

    public Consumidor(Contador contador, int tiempoConsumicion)
    {
        this.contador = contador;
        this.TIEMPO_CONSUMICION = tiempoConsumicion;
    }

    @Override
    public void run()
    {
        while (true)
        {
            if (contador.cupoDeOperacionesAlcanzado())
            {
                break;
            }
            contador.decrementar();
            try
            {
                Thread.sleep(TIEMPO_CONSUMICION);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}
