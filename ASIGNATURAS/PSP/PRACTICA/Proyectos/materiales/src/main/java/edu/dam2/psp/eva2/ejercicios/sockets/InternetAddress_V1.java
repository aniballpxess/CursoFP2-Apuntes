package edu.dam2.psp.eva2.ejercicios.sockets;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetAddress_V1
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