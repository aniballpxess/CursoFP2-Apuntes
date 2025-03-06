package edu.dam2.psp.eva2.ejercicios.sincronizacion.banco;

public class Banco
{
    public static void main(String[] args)
    {
        Cuenta cuenta = new Cuenta(1000);
        Runnable cliente_A = new Cliente(cuenta, 55, "Antonio");
        Runnable cliente_B = new Cliente(cuenta, 25, "Bartolomeo");
        Thread movimientos_clienteA = new Thread(cliente_A, "Movimientos de cliente_A");
        Thread movimientos_clienteB = new Thread(cliente_B, "Movimientos de cliente_B");
        movimientos_clienteA.start();
        movimientos_clienteB.start();
        try
        {
            movimientos_clienteA.join();
            movimientos_clienteB.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Saldo final: " + cuenta.getSaldo());
        System.out.println("Número de reintegros: " + cuenta.numero_reintegros);
    }
}
