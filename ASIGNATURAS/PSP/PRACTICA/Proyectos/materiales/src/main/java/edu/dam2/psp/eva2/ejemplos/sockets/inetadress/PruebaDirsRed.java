package edu.dam2.psp.eva2.ejemplos.sockets.inetadress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PruebaDirsRed
{
    public static void main(String[] args)
    {
        InetAddress dir = null;
        System.out.println("\nSalida para LOCALHOST");
        try
        {
            dir = InetAddress.getLocalHost();
            pruebaMetodos(dir);
            System.out.println("\nSalida para un nombre de equipo");
            dir = InetAddress.getByName("A4-PROFESOR");
            pruebaMetodos(dir);
            System.out.println("\nSalida para GOOGLE");
            dir = InetAddress.getByName("www.googe.es");
            pruebaMetodos(dir);
            System.out.println("\nSalida para madrid org");
            dir = InetAddress.getByName("www.madrid.org");
            pruebaMetodos(dir);
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }

    private static void pruebaMetodos(InetAddress dir)
    {
        System.out.println("\tMétodo getByName(): " + dir);
        System.out.println("\tMétodo getHostName():" + dir.getHostName());
        System.out.println("\tMétodo toString(): " + dir.toString());
        System.out.println("\tMétodo getCanonicalHostName():" + dir.getCanonicalHostName());
    }
}
