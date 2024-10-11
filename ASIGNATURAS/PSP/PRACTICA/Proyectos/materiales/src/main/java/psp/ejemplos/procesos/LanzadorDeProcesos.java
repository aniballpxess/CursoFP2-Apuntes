package psp.ejemplos.procesos;

import java.io.IOException;

public class LanzadorDeProcesos
{
    public void ejecutar(String rutaProceso)
    {
        try
        {
            ProcessBuilder pb = new ProcessBuilder(rutaProceso);
            pb.start();
        }
        catch (IOException ioe)
        {
            System.err.println("Se ha producido un error IO");
            ioe.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        LanzadorDeProcesos ldp = new LanzadorDeProcesos();
        ldp.ejecutar(args[0]);
        System.out.println("Programa lanzado con éxito.");
    }

}
