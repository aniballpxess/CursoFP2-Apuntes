package psp.utiles;

import static psp.utiles.Funciones.printErrorLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class LanzadorDeProgramas
{
    private static final Map<String, String> mapaProgramasComandos = Map.ofEntries(
        Map.entry("ale", "java -jar bin\\psp\\materiales-0.1_aleatorios-1.jar"),
        Map.entry("div", "java -jar bin\\psp\\materiales-0.1_divisores.jar"),
        Map.entry("dob", "java -jar bin\\psp\\materiales-0.1_doble-1.jar"),
        Map.entry("eur", "java -jar bin\\psp\\materiales-0.1_europa-1.jar"),
        Map.entry("may", "java -jar bin\\psp\\materiales-0.1_mayusculas-1.jar"),
        Map.entry("med", "java -jar bin\\psp\\materiales-0.1_media-1.jar"),
        Map.entry("sum", "java -jar bin\\psp\\materiales-0.1_sumatorio.jar")
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
        System.setProperty("user.dir", Parametros.materialesPath.toString());
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
        String packageName = LanzadorDeProgramas.class.getPackageName();
        String className = LanzadorDeProgramas.class.getSimpleName();
        String errorLogFile = packageName + "." + className + "-error.log";
        String errorLogDir = "logs";
        File errorLog = new File(errorLogDir, errorLogFile);
        //System.out.println(errorLog.getPath());
        //System.out.println(errorLog.getAbsolutePath());
        try
        {
            System.setErr(new PrintStream(new FileOutputStream(errorLog , true)));
        }
        catch (FileNotFoundException e)
        {
            for (StackTraceElement element : e.getStackTrace()) 
            {
                printErrorLine(element.toString());
            }
        }
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
