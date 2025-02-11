package edu.dam2.psp.eva2.ejercicios.sockets;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetAddress
{
    public static void main(String[] args) throws UnknownHostException
    {

        InetAddress host = InetAddress.getByName("My-Personal-PC");
        System.out.println("Host con tipo InetAddres: " + host);
        System.out.println("IP: " + host.getHostAddress());
        System.out.println("Nombre: " + host.getHostName());
        System.out.println("Canonical Nombre: " + host.getCanonicalHostName());
        System.out.println("Host con tipo toString: " + host.toString());

        InetAddress[] dirs = InetAddress.getAllByName("My-Personal-PC");
        for (int i = 0; i < dirs.length; i++)
        {
            System.out.println("posicion " + i + " de AllByName : " + dirs[i]);
        }

    }

}
