package edu.dam2.psp.eva1.ejercicios.divisores;

import static edu.dam2.psp.utiles.Funciones.lanzarPeticionLeerEntrada;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        int num;
        System.out.println("Calculo de los divisores de un numero.\n");
        while (true)
        {
            try
            {
                num = Integer.parseInt(lanzarPeticionLeerEntrada(escaner, "Numero: "));
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("No has introducido un numero.");
            }
        }
        System.out.println("Numero de divisores: " + Calculador.cuantosDivisores(num));
        System.exit(0);
    }
}
