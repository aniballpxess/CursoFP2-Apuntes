package edu.dam2.psp.eva1.ejemplos.procesos;

public class DestruirProcesos
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.err.println("No se pasado el programa a ejecutar.");
            System.exit(-20);
        }
        try
        {
            Runtime runtime = Runtime.getRuntime();
            Process proceso = runtime.exec(args);
            proceso.destroy();
            System.out.println(proceso.exitValue());
        }
        catch (Exception e)
        {
            System.err.println("Error inesperado.");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
