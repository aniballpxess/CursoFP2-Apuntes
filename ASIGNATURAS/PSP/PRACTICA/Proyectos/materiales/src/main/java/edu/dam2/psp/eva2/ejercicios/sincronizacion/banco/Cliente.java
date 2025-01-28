package edu.dam2.psp.eva2.ejercicios.sincronizacion.banco;

public class Cliente implements Runnable
{
    private Cuenta cuenta;
    private int cantidad;
    private int total;
    private String nombre;

    public Cliente(Cuenta cuenta, int cantidad, String nombre)
    {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.total = 0;
    }

    @Override
    public void run()
    {
        while (cuenta.haySuficienteSaldo(cantidad))
        {
            cuenta.reintegro(cantidad);
            total = total + cantidad;
            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("El cliente " + nombre + " ha retirado " + total + " euros");
    }

}
