package edu.dam2.psp.eva1.tarea;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class LanzadorSuma
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        String ficheroErrores;
        String ficheroSalida;
        int contador = 1;
        try
        {
            while (true) {
                ficheroErrores = "C:\\Windows\\Temp\\P1errores.txt";
                ficheroSalida = "C:\\WIndows\\Temp\\P1resultado" + contador + ".txt";
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", "./Suma.jar");
                pb.redirectError(new File(ficheroErrores));
                pb.redirectOutput(new File(ficheroSalida));
                Process proceso = pb.start();
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));

                System.out.print("¿Quieres sumar dos numeros? (S/N) ");
                String input = escaner.nextLine();
                if (input.equalsIgnoreCase("N")) {
                    break;
                }
                
                System.out.println("Introduce dos numeros para sumarlos.");
                System.out.println("Fichero de errores en: " + ficheroErrores);
                System.out.println("Fichero con el resultado en: " + ficheroSalida);
                System.out.print("Primer numero: ");
                pw.println(escaner.nextLine());
                pw.flush();
                System.out.print("Segundo numero: ");
                pw.println(escaner.nextLine());
                pw.flush();
                contador++;
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        escaner.close();
    }
}
