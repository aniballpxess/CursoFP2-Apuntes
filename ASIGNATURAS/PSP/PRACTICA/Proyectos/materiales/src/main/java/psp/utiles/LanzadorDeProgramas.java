package psp.utiles;

import java.util.Scanner;

public class LanzadorDeProgramas
{
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
                System.out.println("Cerrando...");
                break;
            }
            if (entrada.equalsIgnoreCase("E"))
            {
                System.out.print("""
                        Cargando lista de programas...
                        ###############################################
                        Programas disponibles:

                        sum - ejecuta "sumatorio"
                        div - ejecuta "divisores"
                        ale - ejecuta "aleatorios"
                        dob - ejecuta "doble"
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

    public static String[] escogerPrograma(Scanner escaner)
    {
        String programa;
        String[] listaDeProgramas = { 
            "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_sumatorio.jar",
            "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_divisores.jar",
            "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_aleatorios-1.jar",
            "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\binarios\\materiales-0.1_doble.jar", 
        };
        while (true)
        {
            programa = Funciones.leerEntrada(escaner, "Escoge un programa: ");
            boolean seleccionado = true;
            switch (programa)
            {
            case "sum":
                programa = listaDeProgramas[0];
                break;
            case "div":
                programa = listaDeProgramas[1];
                break;
            case "ale":
                programa = listaDeProgramas[2];
                break;
            case "dob":
                programa = listaDeProgramas[3];
                break;
            default:
                seleccionado = false;
                break;
            }
            if (seleccionado)
            {
                break;
            }
        }
        return programa.split(" ");
    }
}
