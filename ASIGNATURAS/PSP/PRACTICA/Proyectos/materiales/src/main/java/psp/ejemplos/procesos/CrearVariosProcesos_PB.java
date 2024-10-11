package psp.ejemplos.procesos;

import java.io.IOException;

public class CrearVariosProcesos_PB
{
    public static void main(String[] args)
    {
        ProcessBuilder pb = new ProcessBuilder(args);
        for (int i = 0; i < 5; i++)
        {
            try
            {
                pb.start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
