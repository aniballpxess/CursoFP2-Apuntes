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
        Scanner escaner = new Scanner(System.in);
        String[] generadorDeNumeros = { "java", "-jar", "C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_aleatorios-2.jar" };
        try
        {
            while (true)
            {
                Process proceso = new ProcessBuilder(generadorDeNumeros).start();
                InputStream is = proceso.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                proceso.waitFor();
                System.out.println("Numero Generado: " + br.readLine());
                String entrada = leerEntrada(escaner, "Introduce FIN para salir: ");
                if (entrada.equalsIgnoreCase("FIN"))
                {
                    break;
                }
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        } finally {
            
            escaner.close();
        }
    }
}
