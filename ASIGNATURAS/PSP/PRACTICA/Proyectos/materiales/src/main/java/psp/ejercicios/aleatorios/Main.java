package psp.ejercicios.aleatorios;

import static psp.utiles.Funciones.lanzarPeticionLeerEntrada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        String[] generadorDeNumeros = { "java", "-jar", "bin\\psp\\materiales-0.1_aleatorios-2.jar" };
        try
        {
            while (true)
            {
                Process proceso = new ProcessBuilder(generadorDeNumeros).start();
                BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                proceso.waitFor();
                System.out.println("Numero Generado: " + br.readLine());
                String entrada = lanzarPeticionLeerEntrada(escaner, "Introduce FIN para salir: ");
                if (entrada.equalsIgnoreCase("FIN"))
                {
                    br.close();
                    break;
                }
            }
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            escaner.close();
        }
    }
}
