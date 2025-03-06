package edu.dam2.psp.eva2.ejercicios.hilos.positivosnegativos;

public class PositivosNegativos
{
    public static void main(String[] args)
    {
        for (int i = 1; i < 4; i++)
        {
            System.out.println("Ejecución número: " + i);

            Thread hilo_numerosPositivos = new Escribir100Positivos_T(100);
            Runnable tarea_numerosNegativos = new Escribir100Negativos_R(100);
            Thread hilo_numerosNegativos = new Thread(tarea_numerosNegativos);

            hilo_numerosPositivos.start();
            hilo_numerosNegativos.start();
            try
            {
                hilo_numerosPositivos.join();
                hilo_numerosNegativos.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("\n--------------------------------------------------");
        }
        System.out.println("Fin del programa.");
    }
}
