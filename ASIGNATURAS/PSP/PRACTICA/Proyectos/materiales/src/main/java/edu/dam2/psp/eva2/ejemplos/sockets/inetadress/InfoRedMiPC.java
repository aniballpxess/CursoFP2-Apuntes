package edu.dam2.psp.eva2.ejemplos.sockets.inetadress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InfoRedMiPC
{
    public static void main(String[] args) throws UnknownHostException
    {

        InetAddress host = InetAddress.getByName("DESKTOP-9IR2FPR");
        System.out.println("Host con tipo InetAddres: " + host);
        System.out.println("IP: " + host.getHostAddress());
        System.out.println("Nombre: " + host.getHostName());
        System.out.println("Nombre canonico: " + host.getCanonicalHostName());
        System.out.println("Host con tipo toString: " + host.toString());

        InetAddress[] dirs = InetAddress.getAllByName("DESKTOP-9IR2FPR");
        for (int i = 0; i < dirs.length; i++)
        {
            System.out.println("posicion " + i + " de AllByName : " + dirs[i]);
        }

    }

}
