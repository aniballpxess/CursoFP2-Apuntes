package psp.utiles;

import java.util.Scanner;

public class LanzadorDeProgramas
{
    public static void main(String[] args)
    {
        System.out.print("""
                -----------------------------------------------
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
                String[] programa = Funciones.escogerPrograma(escaner);
                System.out.println("Iniciando proceso de ejecución.");
                Funciones.ejecutarPrograma(escaner, programa);
            }
        }
        escaner.close();
        System.exit(0);
    }
}
