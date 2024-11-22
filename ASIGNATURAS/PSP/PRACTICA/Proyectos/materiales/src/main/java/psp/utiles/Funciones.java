package psp.utiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Funciones
{
    public static void ejecutarPrograma(Scanner userInput, String[] program)
    {
        try
        {
            Process process = new ProcessBuilder(program).start();
            BufferedReader childError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            BufferedReader childOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            PrintWriter childInput = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));
            while (process.isAlive())
            {
                String newLine = childOutput.readLine();
                if (newLine == null)
                {
                    continue;
                }
                if (newLine.equals("_UIR_"))
                {
                    manejarPeticionLeerEntrada(userInput, childOutput, childInput);
                }
                else
                {
                    System.out.println(newLine);
                }
            }
            while (true)
            {
                String newLine = childError.readLine();
                if (newLine == null)
                {
                    break;
                }
                printErrorLine(newLine);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String leerEntrada(Scanner escaner, String mensaje)
    {
        System.out.print(mensaje);
        return escaner.nextLine();
    }

    public static void manejarPeticionLeerEntrada(Scanner escaner, BufferedReader br, PrintWriter pw) throws IOException
    {
        System.out.print(br.readLine());
        pw.println(escaner.nextLine());
        pw.flush();
    }

    public static String lanzarPeticionLeerEntrada(Scanner escaner, String mensaje)
    {
        System.out.println("_UIR_");
        System.out.println(mensaje);
        return escaner.nextLine();
    }

    public static void pasarPeticionLeerEntrada(Scanner escaner, BufferedReader br, PrintWriter pw) throws IOException
    {
        System.out.println("_UIR_");
        System.out.println(br.readLine());
        pw.println(escaner.nextLine());
        pw.flush();
    }

    public static void printErrorLine(String line)
    {
        String time = LocalDateTime.now().format(Parametros.NORMAL_FORMAT);
        System.err.println(time + " - " + line);
    }
}
