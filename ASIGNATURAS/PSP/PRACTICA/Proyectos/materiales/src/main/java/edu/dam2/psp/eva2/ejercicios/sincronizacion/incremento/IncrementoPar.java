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
            if (contador.getValor() == limite)
            {
                break;
            }
            if (contador.getValor() % 2 == 0)
            {
                contador.incrementa();
                int valorContador = contador.getValor();
                System.out.println(threadName + " incrementa el contador a " + valorContador);
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    for (StackTraceElement element : e.getStackTrace())
                    {
                        System.err.println(element.toString());
                    }
                }
            }
            else {
                System.out.println(threadName + " INTENTA incrementar el contador.");
            }
        }
    }

}
