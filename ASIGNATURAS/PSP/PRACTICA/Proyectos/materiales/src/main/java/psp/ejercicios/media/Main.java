package psp.ejercicios.media;

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
        String[] calculadorMedia = { "java", "-jar", "bin\\psp\\materiales-0.1_media-2.jar" };
        System.out.print("""
                Introduce numeros para calcular la media.
                La media se calcula tras recibir un 0.

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
            String resultadoMedia = br.readLine();
            if (resultadoMedia == null)
            {
                System.out.println("No se ha introducido ningun numero.");
            }
            else
            {
                System.out.println("Media: " + resultadoMedia);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        escaner.close();
    }
}
