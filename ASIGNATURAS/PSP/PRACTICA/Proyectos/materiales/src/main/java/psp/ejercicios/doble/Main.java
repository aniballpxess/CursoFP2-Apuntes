package psp.ejercicios.doble;

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
        int num;
        System.out.println("Calculo del doble de un numero.\n");
        while (true)
        {
            try
            {
                num = Integer.parseInt(lanzarPeticionLeerEntrada(escaner, "Numero: "));
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("No has introducido un numero.");
            }
        }
        try
        {
            String[] calculadorDoble = { "java", "-jar", "C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_doble-2.jar" };
            Process proceso = new ProcessBuilder(calculadorDoble).start();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            pw.println(num);
            pw.flush();
            proceso.waitFor();
            System.out.println("Doble del numero: " + br.readLine());
            pw.close();
            br.close();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
