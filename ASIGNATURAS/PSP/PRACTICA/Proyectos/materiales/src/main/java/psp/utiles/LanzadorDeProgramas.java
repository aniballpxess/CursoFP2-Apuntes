package psp.utiles;

import java.util.Map;
import java.util.Scanner;

public class LanzadorDeProgramas
{
    private static final Map<String, String> mapaProgramasComandos = Map.ofEntries(
        Map.entry("ale", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_aleatorios-1.jar"),
        Map.entry("div", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_divisores-1.jar"),
        Map.entry("dob", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_doble-1.jar"),
        Map.entry("eur", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_europa-1.jar"),
        Map.entry("may", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_mayusculas-1.jar"),
        Map.entry("med", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_media-1.jar"),
        Map.entry("sum", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_sumatorio-1.jar")
    );
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        System.out.print("""
                ###############################################
                LANZADOR DE PROGRAMAS - Version 0.2 - Multipro
                ###############################################
                Acciones Disponibles:

                S (Salir)    - termina el lanzador
                E (Ejecutar) - inicia la ejecución de programas
                -----------------------------------------------
                """);
        while (true)
        {
            String entrada = Funciones.leerEntrada(escaner, "Acción: ");
            if (entrada.equalsIgnoreCase("S"))
            {
                System.out.print("""
                        Cerrando...
                        ###############################################
                        """);
                break;
            }
            if (entrada.equalsIgnoreCase("E"))
            {
                System.out.print("""
                        Cargando lista de programas...
                        ###############################################
                        Programas disponibles:

                        ale - ejecuta "aleatorios"
                        div - ejecuta "divisores"
                        dob - ejecuta "doble"
                        eur - ejecuta "europa"
                        may - ejecuta "mayusculas"
                        med - ejecuta "media"
                        sum - ejecuta "sumatorio"
                        -----------------------------------------------
                        """);
                String[] programa = escogerPrograma(escaner);
                System.out.print("""
                        Iniciando ejecución de programa...
                        ###############################################
                        """);
                Funciones.ejecutarPrograma(escaner, programa);
                System.out.print("""
                        -----------------------------------------------
                        Programa terminado.
                        Volviendo al menú principal...
                        ###############################################
                        Acciones Disponibles:

                        S (Salir)    - termina el lanzador
                        E (Ejecutar) - inicia la ejecución de programas
                        -----------------------------------------------
                        """);
            }
        }
        escaner.close();
        System.exit(0);
    }

    private static String[] escogerPrograma(Scanner escaner)
    {
        while (true)
        {
            String programa = Funciones.leerEntrada(escaner, "Escoge un programa: ");
            String comando = mapaProgramasComandos.get(programa.toLowerCase());
            if (comando != null) {
                return comando.split(" ");
            }
        }
    }
}
