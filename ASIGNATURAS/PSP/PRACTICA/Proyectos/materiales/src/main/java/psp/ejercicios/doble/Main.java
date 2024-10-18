package psp.ejercicios.doble;

import static psp.utiles.Funciones.peticionLeerEntrada;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        int num;
        System.out.println("Calculo  del doble de un numero.\n");
        while (true)
        {
            try
            {
                num = Integer.parseInt(peticionLeerEntrada(escaner, "Numero: "));
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("No has introducido un numero.");
            }
            catch (Exception e)
            {
                System.out.println("Error inesperado.");
            }
        }
        System.out.println(num + " x 2 = " + num * 2);
        System.exit(0);
    }
}
