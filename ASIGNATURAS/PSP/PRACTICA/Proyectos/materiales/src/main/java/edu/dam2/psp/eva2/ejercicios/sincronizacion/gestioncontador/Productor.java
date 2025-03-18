package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestioncontador;

public class Productor implements Runnable
{
    private Contador contador;
    private final int TIEMPO_PRODUCCION;

    public Productor(Contador contador, int tiempoProduccion)
    {
        this.contador = contador;
        this.TIEMPO_PRODUCCION = tiempoProduccion;
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
            contador.incrementar();
            try
            {
                Thread.sleep(TIEMPO_PRODUCCION);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}
