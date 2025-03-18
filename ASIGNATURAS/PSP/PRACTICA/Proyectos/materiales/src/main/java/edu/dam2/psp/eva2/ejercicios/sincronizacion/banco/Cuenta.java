package edu.dam2.psp.eva2.ejercicios.sincronizacion.banco;

public class Cuenta {
    private int saldo;
    public int numero_reintegros;

    public Cuenta(int saldo_inicial)
    {
        this.saldo = saldo_inicial;
        this.numero_reintegros = 0;
    }

    public int getSaldo()
    {
        return saldo;
    }

    synchronized public void reintegro(int cantidad)
    {
        saldo = saldo - cantidad;
        numero_reintegros++;
    }

    synchronized public boolean haySuficienteSaldo(int cantidad)
    {
        return saldo >= cantidad;
    }
}
