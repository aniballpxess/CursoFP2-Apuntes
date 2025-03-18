package edu.dam2.psp.eva1.ejercicios.europa;

import static edu.dam2.psp.utiles.Funciones.lanzarPeticionLeerEntrada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        String[] relacionPaisCapitalEuropa = { "java", "-jar", "bin\\psp\\materiales-0.1_europa-2.jar" };
        System.out.println("Introduce un pais de Europa (en inglés) para saber su capital.\n");
        try
        {
            Process proceso = new ProcessBuilder(relacionPaisCapitalEuropa).start();
            BufferedReader br = proceso.inputReader();
            PrintWriter pw = new PrintWriter(proceso.outputWriter());
            String pais = lanzarPeticionLeerEntrada(escaner, "Pais: ");
            pw.println(pais);
            pw.flush();
            String capital = br.readLine();
            if (capital == null) 
            {
                System.out.println("Pais no reconocido.");
            } 
            else 
            {
                System.out.println("Capital: " + capital);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        escaner.close();
    }
}
