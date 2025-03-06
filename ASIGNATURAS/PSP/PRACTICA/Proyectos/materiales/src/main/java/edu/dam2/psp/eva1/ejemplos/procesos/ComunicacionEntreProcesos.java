package edu.dam2.psp.eva1.ejemplos.procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ComunicacionEntreProcesos
{
    public static void main(String args[]) throws IOException
    {
        Process proceso = new ProcessBuilder(args).start();
        InputStream is = proceso.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Salida de proceso " + Arrays.toString(args) + ":");
        while (true)
        {
            String linea = br.readLine();
            if (linea == null)
            {
                break;
            }
            System.out.println(linea);
        }
        br.close();
        isr.close();
        is.close();
    }
}
