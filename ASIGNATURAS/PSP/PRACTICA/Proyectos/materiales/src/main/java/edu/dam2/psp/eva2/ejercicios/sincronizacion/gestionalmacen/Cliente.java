package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestionalmacen;

import java.util.Random;

public class Cliente implements Runnable
{
    private Almacen almacen;
    private final int INTENTOS_MAXIMOS;
    private final String ID_CLIENTE;

    public Cliente(Almacen almacen, int intentosMaximos, String idCLiente)
    {
        this.almacen = almacen;
        this.INTENTOS_MAXIMOS = intentosMaximos;
        this.ID_CLIENTE = idCLiente;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < INTENTOS_MAXIMOS; i++)
        {
            if (almacen.sePuedePasar())
            {
                this.esperar(100); // Para ajustar los resultados de la simulación
                boolean productoRecogido = almacen.recogerProducto();
                almacen.salirDelAlmacen();
                if (productoRecogido)
                {
                    System.out.println(ID_CLIENTE + ": pude recoger el producto.");
                    return;
                }
                System.out.println(ID_CLIENTE + ": no quedaban productos.");
                return;
            }
            this.esperar(2000); // Para ajustar los resultados de la simulación
        }
        System.out.println(ID_CLIENTE + ": tuve que esperar mucho tiempo y me fui.");
    }

    private void esperar(int tiempoMaximo)
    {
        int tiempoEspera = new Random().nextInt(tiempoMaximo);
        try
        {
            Thread.sleep(tiempoEspera);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}