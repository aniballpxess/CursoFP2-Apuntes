package psp.utiles;

import java.util.Scanner;

public class LanzadorDeProgramas
{
    public static void main(String[] args)
    {
        System.out.println("""

                LANZADOR DE PROGRAMAS - Version 0.2 - Multipro
                -----------------------------------------------
                Acciones Disponibles:
                S (Salir)    - termina el lanzador
                E (Ejecutar) - inicia la ejecución de programas
                -----------------------------------------------
                """);
        Scanner escaner = new Scanner(System.in);
        while (true)
        {
            String entrada = Funciones.leerEntrada(escaner, "Acción: ");
            if (entrada.equalsIgnoreCase("S"))
            {
                System.out.println("Cerrando.");
                break;
            }
            if (entrada.equalsIgnoreCase("E"))
            {
                System.out.println("Iniciando proceso de ejecución.");
                String[] programa = Funciones.leerPrograma(escaner);
                Funciones.ejecutarPrograma(escaner, programa);
            }
        }
        escaner.close();
        System.exit(0);
    }
}
