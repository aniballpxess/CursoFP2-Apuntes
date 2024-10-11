package psp.ejercicios.aleatorios;

import static psp.utiles.Funciones.leerEntrada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Scanner escaner = new Scanner(System.in);
            String[] generadorDeNumeros = { "java", "-jar", "C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_aleatorios-2.jar" };
            while (true)
            {
                Process proceso = new ProcessBuilder(generadorDeNumeros).start();
                try (InputStream is = proceso.getInputStream();
                     InputStreamReader isr = new InputStreamReader(is);
                     BufferedReader br = new BufferedReader(isr))
                {
                    proceso.waitFor();
                    System.out.println("Numero Generado: " + br.readLine());
                    System.out.flush();
                    String entrada = leerEntrada(escaner, "Introduce FIN para salir: ");
                    if (entrada.equalsIgnoreCase("FIN"))
                    {
                         break;
                    }
                }
            }
            escaner.close();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
