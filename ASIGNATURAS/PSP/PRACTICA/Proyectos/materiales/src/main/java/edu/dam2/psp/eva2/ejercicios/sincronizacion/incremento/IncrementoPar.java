package edu.dam2.psp.eva2.ejercicios.sincronizacion.incremento;

public class IncrementoPar implements Runnable {
    private Contador contador;
    private final int limite;

    public IncrementoPar(Contador contador)
    {
        this.contador = contador;
        this.limite = contador.getLimite();
    }

    @Override
    public void run()
    {
        String threadName = Thread.currentThread().getName();
        while (true)
        {
            int valorContador = contador.getValor(threadName);
            if (valorContador == limite)
            {
                break;
            }
            if (valorContador % 2 == 0)
            {
                contador.incrementa(threadName);
            }
        }
    }

}
