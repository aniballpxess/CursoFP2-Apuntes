package ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LanzadorDoble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		try {
			
		//creo un proceso hijo, que le pasar� por los argumentos del main (y ese proceso hijo ser� la llamada a doble.jar)
		//java-jar ruta programa.jar
			ProcessBuilder pb=new ProcessBuilder(args);
			
			//ejecuto el proceso creado anteriormente
			Process proceso=pb.start();
			
			
			//Con el m�todo getInputStream estamos leyendo la salida est�ndar del hijo
			InputStream is=proceso.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			String line;
			while((line=br.readLine())!=null) {
				System.out.println("El doble es:"+line);
			}
			br.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
