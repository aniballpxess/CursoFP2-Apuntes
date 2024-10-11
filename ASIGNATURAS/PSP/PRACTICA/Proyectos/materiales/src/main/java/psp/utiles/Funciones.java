package psp.utiles;

import java.io.IOException;
import java.util.Scanner;

public class Funciones
{
    public static void ejecutarPrograma(String[] programa)
    {
        try
        {
            Process proceso = new ProcessBuilder(programa).start();
            proceso.waitFor();
        }
        catch (IOException | InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static String leerEntrada(Scanner escaner, String mensaje)
    {
        System.out.print(mensaje);
        return escaner.nextLine();
    }
    public static String[] leerPrograma(Scanner escaner)
    {
        // IMPLEMENTAR ESCOGER TIPO DE PROGRAMA .jar/.war/.exe/.js/...
        // ^ parametro tipo String llamado "tipo"
        String[] programa = new String[4];
        System.out.println("Datos del programa:");
        programa[0] = "java";
        programa[1] = "-jar";
        programa[2] = leerEntrada(escaner, "Ruta: ");
        programa[3] = leerEntrada(escaner, "Argumentos: ");
        return programa;
    }
}
