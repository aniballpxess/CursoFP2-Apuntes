package edu.dam2.psp.eva2.ejemplos.sockets.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Url_V2
{

	public static void main(String[] args) throws IOException
	{

		URL url = null;
		URL url2 = null;

		try
		{
			url = new URL("file:///C://Users//laura//prueba.txt");
			pruebaMetodosURLConnection(url);
			url2 = new URL("file:///C://Users//laura//prueba2.txt");
			pruebaMetodosURLConnection(url2);

		}
		catch (MalformedURLException e)
		{
			System.err.println("\nNo se encuentra la URL");
		}

	}

	private static void pruebaMetodosURLConnection(URL url) throws IOException
	{

		// Una forma de leer el contenido con openStream

		String linea;

		System.out.println("leer de la url con openStream");

		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream())); // leer de la url con openStream
		while ((linea = br.readLine()) != null)
			System.out.println(linea);
		br.close();

		System.out.println("_____________________________");
		System.out.println("\n");
		System.out.println("leer de la url con getContent");

		br = new BufferedReader(new InputStreamReader((InputStream) url.getContent())); // leer de la url con getContent
		while ((linea = br.readLine()) != null)
			System.out.println(linea);
		br.close();

		String user = url.getUserInfo();
		System.out.println("_____________________________\n");
		System.out.println("Usuario conectado:" + user + "\n\n******");

		System.out.println("Get " + url.getAuthority());

	}

}