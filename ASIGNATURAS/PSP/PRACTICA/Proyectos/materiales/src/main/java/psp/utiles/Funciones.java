package psp.utiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Funciones {
    public static void ejecutarPrograma(Scanner escaner, String[] programa) {
        try {
            Process proceso = new ProcessBuilder(programa).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(proceso.getOutputStream()));
            while (proceso.isAlive()) {
                String linea = br.readLine();
                if (!linea.equals(null)) {
                    if (linea.equals("-UserInputRequest-")) {
                        leerEntrada(escaner, br, pw);
                    } else {
                        System.out.println(linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String leerEntrada(Scanner escaner, String mensaje) {
        System.out.print(mensaje);
        return escaner.nextLine();
    }

    public static void leerEntrada(Scanner escaner, BufferedReader br, PrintWriter pw) throws IOException {
        System.out.print(br.readLine());
        pw.println(escaner.nextLine());
    }

    public static String peticionLeerEntrada(Scanner escaner, String mensaje) {
        System.out.println("-UserInputRequest-");
        System.out.println(mensaje);
        return escaner.next();
    }

    public static String[] escogerPrograma(Scanner escaner) {
        // IMPLEMENTAR ESCOGER TIPO DE PROGRAMA .jar/.war/.exe/.js/...
        // ^ parametro tipo String llamado "tipo"
        String[] listaDeProgramas = {
            "java -jar ",
            "java -jar ",
            "java -jar ",
            "java -jar ",
        };
        System.out.print("""
                -----------------------------------------------
                Lista de programas
                -----------------------------------------------
                ale - ejecuta "aleatorios"
                dob - ejecuta "doble"
                div - ejecuta "divisores"
                sum - ejecuta "sumatorio"
                -----------------------------------------------
                """);
        String programa = leerEntrada(escaner, "Selecciona un programa de la lista: ");
        return programa.split(" ");
    }
}
