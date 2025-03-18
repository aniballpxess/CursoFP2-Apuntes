package edu.dam2.psp.eva2.ejercicios.hilos.positivosnegativos;

public class Escribir100Negativos_R implements Runnable
{

    private final int CANTIDAD;

    public Escribir100Negativos_R(int CANTIDAD)
    {
        this.CANTIDAD = CANTIDAD;
    }

    @Override
    public void run()
    {
        for (int i = 1; i <= CANTIDAD; i++)
        {
            int numero = -i;
            System.out.print(numero + " ");
        }
        System.out.print( "*** ");
    }

}
