package edu.dam2.psp.eva1.ejercicios.extremos;

import static edu.dam2.psp.utiles.Funciones.lanzarPeticionLeerEntrada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        String[] calculadorMedia = { "java", "-jar", "bin\\psp\\materiales-0.1_extremos-2.jar" };
        System.out.print("""
                Introduce numeros para saber cual es el menor y cual es el mayor.
                El mayor y el menor se calculan tras recibir un 0.

                """);
        try
        {
            Process proceso = new ProcessBuilder(calculadorMedia).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
            while (true)
            {
                try
                {
                    int inputInt = Integer.parseInt(lanzarPeticionLeerEntrada(escaner, "Numero: "));
                    pw.println(inputInt);
                    pw.flush();
                    if (inputInt == 0)
                    {
                        break;
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Eso no es un numero.");
                    e.printStackTrace();
                }
            }
            String menorNumero = br.readLine();
            String mayorNumero = br.readLine();
            if (menorNumero == null)
            {
                System.out.println("No se ha introducido ningun numero.");
            }
            else
            {
                System.out.println("Menor: " + menorNumero);
                System.out.println("Mayor: " + mayorNumero);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        escaner.close();
    }
}
