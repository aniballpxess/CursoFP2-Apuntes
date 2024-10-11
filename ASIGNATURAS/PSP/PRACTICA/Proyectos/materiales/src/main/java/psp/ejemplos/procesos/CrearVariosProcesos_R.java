package psp.ejemplos.procesos;

import java.io.IOException;

public class CrearVariosProcesos_R
{
    public static void main(String[] args)
    {
        Runtime runtime = Runtime.getRuntime();
        for (int i = 0; i < 5; i++)
        {
                try
                {
                    runtime.exec(args);
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }

        }
    }
}
