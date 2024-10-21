package psp.ejercicios.mayusculas;

import static psp.utiles.Funciones.leerEntrada;
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
        String[] conversorInputs = { "java", "-jar", "C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_mayusculas-2.jar" };
        System.out.println("Introduce texto para que se convierta a mayusculas.\n");
        try
        {
            Process proceso = new ProcessBuilder(conversorInputs).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
            while (proceso.isAlive())
            {
                String input = peticionLeerEntrada(escaner, "");
                pw.println(input);
                System.out.println(br.readLine());
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        escaner.close();
    }
}
