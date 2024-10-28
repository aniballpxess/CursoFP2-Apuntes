package psp.ejercicios.mayusculas;

import static psp.utiles.Funciones.lanzarPeticionLeerEntrada;

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
        String[] conversorInputs = { "java", "-jar", "materiales-0.1_mayusculas-2.jar" };
        System.out.print("""
                Introduce texto para convertirlo a mayusculas.
                Cuando lee un "." el programa termina.

                """);
        try
        {
            Process proceso = new ProcessBuilder(conversorInputs).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
            boolean finalizado = false;
            while (true)
            {
                String input = lanzarPeticionLeerEntrada(escaner, "");
                if (input.contains("."))
                {
                    String[] inputFrag = input.split("\\.");
                    input = inputFrag[0];
                    finalizado = true;
                }
                pw.println(input);
                pw.flush();
                System.out.println(br.readLine());
                if (finalizado)
                {
                    break;
                }
            }
            br.close();
            pw.close();
            proceso.destroy();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        escaner.close();
    }
}
