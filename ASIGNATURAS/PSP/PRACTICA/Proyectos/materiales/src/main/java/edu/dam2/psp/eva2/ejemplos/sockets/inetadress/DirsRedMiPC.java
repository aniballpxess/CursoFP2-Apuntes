package edu.dam2.psp.eva2.ejemplos.sockets.inetadress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DirsRedMiPC
{
    public static void main(String[] args)
    {
        InetAddress dir;
        try
        {
            dir = InetAddress.getLocalHost();
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (InetAddress ia : direcciones)
                System.out.println(ia);
        }
        catch (UnknownHostException e) { e.printStackTrace(); }
    }
}