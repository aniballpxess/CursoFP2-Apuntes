package edu.dam2.psp.eva2.ejemplos.sockets.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Url_V1 {

	public static void main(String[] args) throws IOException {
		//probar cosas que se pueden hacer con una url
		
		URL url=null;
			
		System.out.println("Usamos diferentes constructores de url");

		try {
		
			url= new URL("https://aulavirtual32.educa.madrid.org/ies.barajas.madrid/my/courses.php");
			pruebaMetodosURL(url);
		
			url= new URL("file:///C://Users//laura//prueba.txt");
			pruebaMetodosURL(url);
			
			url= new URL("http", "docs.oracle.com", "/javase/8"); //Otra forma de pasar la url protocol host file
			pruebaMetodosURL(url);
			
			url= new URL("KK"); //No est� construida correctamente, no se indica protocolo....
			pruebaMetodosURL(url); 
			
		} catch (MalformedURLException e) {
			System.err.println("\nNo se encuentra la URL");
		}

	}//main

	private static void pruebaMetodosURL(URL url) {

		System.out.println("\nURl completa: "+url.toString());
		System.out.println("\tgetProtocol: "+url.getProtocol());
		System.out.println("\tgetPort: "+url.getPort() );
		System.out.println("\tgetHost: "+url.getHost());
		System.out.println("\tgetFile: "+url.getFile());
		System.out.println("\tgetUserInfo: "+url.getUserInfo());
		System.out.println("\tgetPath: "+url.getPath());
		System.out.println("\tgetAuthority: "+url.getAuthority());
		System.out.println("\tgetQuery: "+url.getQuery());
	}
	
	
}



