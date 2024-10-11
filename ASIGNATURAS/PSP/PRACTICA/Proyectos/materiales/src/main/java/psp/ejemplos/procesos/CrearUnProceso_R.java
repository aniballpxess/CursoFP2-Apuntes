package psp.ejemplos.procesos;

public class CrearUnProceso_R
{
    public static void main(String[] args)
    {
        try
        {
            if (args.length == 0)
                throw new IllegalArgumentException("No has pasado ningún argumento.");

            if (args.length != 1)
                throw new IllegalArgumentException("Este programa solo ejecuta un proceso");

            Runtime runtime = Runtime.getRuntime();
            Process proceso = runtime.exec(args);
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
