package psp.utiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Funciones
{
    public static void ejecutarPrograma(Scanner escaner, String[] programa)
    {
        try
        {
            Process proceso = new ProcessBuilder(programa).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
            while (proceso.isAlive())
            {
                String linea = br.readLine();
                if (linea == null)
                {
                    continue;
                }
                if (linea.equals("_UIR_"))
                {
                    leerEntrada(escaner, br, pw);
                }
                else 
                {
                    System.out.println(linea);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static String leerEntrada(Scanner escaner, String mensaje)
    {
        System.out.print(mensaje);
        return escaner.nextLine();
    }

    public static void leerEntrada(Scanner escaner, BufferedReader br, PrintWriter pw) throws IOException
    {
        System.out.print(br.readLine());
        pw.println(escaner.nextLine());
        pw.flush();
    }

    public static String peticionLeerEntrada(Scanner escaner, String mensaje)
    {
        System.out.println("_UIR_");
        System.out.println(mensaje);
        return escaner.nextLine();
    }

    public static void peticionLeerEntrada(Scanner escaner, BufferedReader br, PrintWriter pw) throws IOException
    {
        System.out.println("_UIR_");
        System.out.println(br.readLine());
        pw.println(escaner.nextLine());
        pw.flush();
    }
}
