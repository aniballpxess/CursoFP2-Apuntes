package psp.ejercicios.media;

import static psp.utiles.Funciones.peticionLeerEntrada;

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
        String[] calculadorMedia = { "java", "-jar", "C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_media-2.jar" };
        System.out.print("""
                Introduce numeros para calcular la media.
                La media se calcula tras recibir un 0.

                """);
        try
        {
            Process proceso = new ProcessBuilder(calculadorMedia).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
            while (proceso.isAlive())
            {
                try
                {
                    int inputNumber = Integer.parseInt(peticionLeerEntrada(escaner, "Numero: "));
                    pw.println(inputNumber);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Eso no es un numero.");
                    e.printStackTrace();
                }
            }
            System.out.println("Media: " + br.readLine());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        escaner.close();
    }
}
