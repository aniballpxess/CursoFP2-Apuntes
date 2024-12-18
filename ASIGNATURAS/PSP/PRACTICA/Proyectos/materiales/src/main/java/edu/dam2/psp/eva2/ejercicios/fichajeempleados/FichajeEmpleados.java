package edu.dam2.psp.eva2.ejercicios.fichajeempleados;

import static edu.dam2.psp.utiles.Funciones.printErrorLine;

import java.time.LocalTime;
import java.util.Scanner;

public class FichajeEmpleados
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numeroDeEmpleados = 5;
        for (int i = 0; i < numeroDeEmpleados; i++)
        {
            try
            {
                System.out.print("Tu nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Hora de entrada (hh:mm): ");
                LocalTime horaDeEntrada = LocalTime.parse(sc.nextLine());

                Runnable tareaFichar = new FicharEmpleado(nombre, horaDeEntrada);
                Thread fichajeEmpleado = new Thread(tareaFichar);
                fichajeEmpleado.start();
                fichajeEmpleado.join();
            }
            catch (InterruptedException e)
            {
                for (StackTraceElement element : e.getStackTrace())
                {
                    printErrorLine(element.toString());
                }
            }
        }
        sc.close();
    }

}
