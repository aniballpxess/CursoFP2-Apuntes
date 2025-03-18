package edu.dam2.psp.eva2.ejemplos.sockets.clienteservidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        try {
            System.out.println ("Creación del socket servidor");
            ServerSocket serverSocket = new ServerSocket();
            System.out.println ("Realización del bind");
            InetSocketAddress iSA = new InetSocketAddress("172.168.0.157",5555);
            serverSocket.bind(iSA);
            System.out.println ("Espera a que llegue una petición de socket");
            Socket socket = serverSocket.accept();
            System.out.println ("Se ha establecido la conexión");

            InputStream is = socket.getInputStream();
            /*
            Lectura de un array de bytes, la lectura más sencilla.
            */
            byte[] mensaje = new byte[14];//14 bytes, de 'PRIMER MENSAJE'
            is.read(mensaje);
            System.out.println("Mensaje:" + new String(mensaje));
            
            /*
            Lectura de líneas a través del BufferedReader, lectura de líneas
            completas (hasta fin de línea).
            */
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String linea = br.readLine();
            while (linea!=null){
                System.out.println("*" + linea + "*");
                linea = br.readLine();
            }
            
            System.out.println("Cerrando el socket de comunicación");
            socket.close();
            System.out.println("Cerrando el socket servidor");
            serverSocket.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        
    }
    
}
