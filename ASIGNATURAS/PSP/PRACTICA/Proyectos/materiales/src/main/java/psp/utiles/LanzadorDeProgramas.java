package psp.utiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class LanzadorDeProgramas
{
    private static final Map<String, String> mapaProgramasComandos = Map.ofEntries(
        Map.entry("ale", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\materiales\\bin\\psp\\materiales-0.1_aleatorios-1.jar"),
        Map.entry("div", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\materiales\\bin\\psp\\materiales-0.1_divisores-1.jar"),
        Map.entry("dob", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\materiales\\bin\\psp\\materiales-0.1_doble-1.jar"),
        Map.entry("eur", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\materiales\\bin\\psp\\materiales-0.1_europa-1.jar"),
        Map.entry("may", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\materiales\\bin\\psp\\materiales-0.1_mayusculas-1.jar"),
        Map.entry("med", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\materiales\\bin\\psp\\materiales-0.1_media-1.jar"),
        Map.entry("sum", "java -jar C:\\CursoFP2\\ASIGNATURAS\\PSP\\PRACTICA\\Proyectos\\materiales\\bin\\psp\\materiales-0.1_sumatorio-1.jar")
    );

    private static final String launchMSG = """
            ###############################################
            LANZADOR DE PROGRAMAS - Version 0.2 - Multipro
            ###############################################
            Acciones Disponibles:

            S (Salir)    - termina el lanzador
            E (Ejecutar) - inicia la ejecución de programas
            -----------------------------------------------
            """;

    private static final String programListMSG = """
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
            """;

    private static final String programLaunchMSG = """
            Iniciando ejecución de programa...
            ###############################################
            """;
    
    private static final String menuLoadMSG = """
            -----------------------------------------------
            Programa terminado.
            Volviendo al menú principal...
            ###############################################
            Acciones Disponibles:

            S (Salir)    - termina el lanzador
            E (Ejecutar) - inicia la ejecución de programas
            -----------------------------------------------
            """;

    private static final String closeMSG = """
            Cerrando...
            ###############################################
            """;

    public static void main(String[] args)
    {
        crearLogErrores();
        Scanner escaner = new Scanner(System.in);
        System.out.print(launchMSG);
        while (true)
        {
            String entrada = Funciones.leerEntrada(escaner, "Acción: ");
            if (entrada.equalsIgnoreCase("S"))
            {
                System.out.print(closeMSG);
                break;
            }
            if (entrada.equalsIgnoreCase("E"))
            {
                System.out.print(programListMSG);
                String[] programa = escogerPrograma(escaner);
                System.out.print(programLaunchMSG);
                Funciones.ejecutarPrograma(escaner, programa);
                System.out.print(menuLoadMSG);
            }
        }
        escaner.close();
        System.exit(0);
    }

    // ARREGLAR
    private static void crearLogErrores()
    {
        System.out.println();
        System.out.println(Path.of("").toAbsolutePath());
        System.out.println();

        String packageName = LanzadorDeProgramas.class.getPackageName();
        String className = LanzadorDeProgramas.class.getSimpleName();
        String errorLogFile = packageName + "." + className + "-error.log";
        String errorLogDir = ".\\logs\\";
        
        String[] temp = Path.of("").toAbsolutePath().toString().split("\\");
        temp[temp.length - 1].equals("materiales");

        File errorLog = new File(errorLogDir + errorLogFile);
        
        try
        {
            System.setErr(new PrintStream(new FileOutputStream(errorLog , true)));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            errorLogDir = System.getenv("TEMP");
            errorLog = new File(errorLogDir + errorLogFile);
            // System.setErr(new PrintStream(new FileOutputStream(errorLog , true)));
        }
        System.err.println("\n" + Path.of("").toAbsolutePath().toString());
    }

    private static String[] escogerPrograma(Scanner escaner)
    {
        while (true)
        {
            String programa = Funciones.leerEntrada(escaner, "Escoge un programa: ");
            String comando = mapaProgramasComandos.get(programa.toLowerCase());
            if (comando != null) 
            {
                return comando.split(" ");
            }
        }
    }
}
