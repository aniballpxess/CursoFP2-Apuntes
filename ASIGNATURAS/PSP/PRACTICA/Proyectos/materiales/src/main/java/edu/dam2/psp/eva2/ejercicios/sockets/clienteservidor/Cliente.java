package edu.dam2.psp.eva2.ejercicios.sockets.clienteservidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente
{

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Creando socket cliente");
            Socket clientSocket = new Socket();
            System.out.println("Estableciendo conexión");
            InetSocketAddress addr = new InetSocketAddress("172.168.0.157", 5555);
            clientSocket.connect(addr);

            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            List<String> mensajes = new ArrayList<>();
            boolean salir = false;
            boolean enviar = false;
            System.out.println("Servicio de mensajería. Para salir escribe 'exit' en una linea a parte.");
            while (true)
            {
                System.out.println("Escribe tu mensaje. Para enviarlo escribe 'send' en una linea a parte.");
                while (true)
                {
                    System.out.print(">> ");
                    String mensaje = sc.nextLine();
                    salir = mensaje.equals("exit");
                    enviar = mensaje.equals("send");
                    if (salir || enviar)
                    {
                        break;
                    }
                    mensajes.add(mensaje);
                }
                if (salir)
                {
                    break;
                }
                System.out.println("Enviando mensaje");
                for (String m : mensajes)
                {
                    os.write(m.getBytes());
                }
                System.out.println("Mensaje enviado");
                mensajes.clear();
            }
            System.out.println("Liberando recursos.");
            sc.close();
            is.close();
            os.close();
            clientSocket.close();
            System.out.println("Terminado");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

}
