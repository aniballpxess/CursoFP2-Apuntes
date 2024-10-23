package psp.ejercicios.europa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        String[] relacionPaisCapital =
        { "java", "-jar", "C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_europa-2.jar" };
        try
        {
            Process proceso = new ProcessBuilder(relacionPaisCapital).start();
            BufferedReader br = proceso.inputReader();
            PrintWriter pw = new PrintWriter(proceso.outputWriter());
            while (true)
            {

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        escaner.close();
    }
}
