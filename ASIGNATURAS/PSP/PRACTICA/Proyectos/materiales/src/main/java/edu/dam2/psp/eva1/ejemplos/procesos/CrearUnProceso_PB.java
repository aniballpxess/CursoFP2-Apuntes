package edu.dam2.psp.eva1.ejemplos.procesos;

public class CrearUnProceso_PB
{
    public static void main(String[] args)
    {
        try
        {
            if (args.length == 0)
                throw new IllegalArgumentException("No has pasado ningún argumento.");

            if (args.length != 1)
                throw new IllegalArgumentException("Este programa solo ejecuta un proceso");

            ProcessBuilder pb = new ProcessBuilder(args);
            Process proceso = pb.start();
            System.out.println(proceso.pid());
            proceso.waitFor();
            System.out.println(proceso.exitValue());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
