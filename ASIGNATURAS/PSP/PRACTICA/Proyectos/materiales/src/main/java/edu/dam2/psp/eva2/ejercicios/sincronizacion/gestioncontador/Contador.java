package edu.dam2.psp.eva2.ejercicios.sincronizacion.gestioncontador;

public class Contador
{
    private final int OPERACIONES_MAXIMAS;
    private final int CAPACIDAD_MINIMA;
    private final int CAPACIDAD_MAXIMA;
    private int valor;
    private int numOperaciones;

    public Contador(int valorInicial, int capacidadMaxima, int capacidadMinima, int operacionesMaximas)
    {
        this.valor = valorInicial;
        this.CAPACIDAD_MAXIMA = capacidadMaxima;
        this.CAPACIDAD_MINIMA = capacidadMinima;

        this.numOperaciones = 0;
        this.OPERACIONES_MAXIMAS = operacionesMaximas;
    }

    public synchronized void incrementar()
    {
        if (valor < CAPACIDAD_MAXIMA)
        {
            valor++;
            numOperaciones++;
            System.out.println("Incremento del contador a " + valor);
        }
    }

    public synchronized void decrementar()
    {
        if (valor > CAPACIDAD_MINIMA)
        {
            valor--;
            numOperaciones++;
            System.out.println("Decremento del contador a " + valor);
        }
    }

    public synchronized boolean cupoDeOperacionesAlcanzado()
    {
        return numOperaciones >= OPERACIONES_MAXIMAS;
    }
}
