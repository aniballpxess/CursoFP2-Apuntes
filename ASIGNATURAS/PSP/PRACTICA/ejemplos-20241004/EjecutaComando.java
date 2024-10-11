package ejemplos;

import java.io.File;
import java.io.IOException;
import java.util.Map;
public class EjecutaComando {
	private static java.util.List<String> comando;
	public static void main(String[] args) throws IOException {
		try{
			ProcessBuilder p = new ProcessBuilder ("CMD","/C", "DI");
			
			//p.redirectOutput(new File("C:\\Windows\\temp\\ej0a.txt")); 
			//p.redirectError(new File("C:\\Windows\\temp\\error.txt")); //cambiar DIR por DI y observa que se crea el fichero con el error.
			comando = p.command();
			System.out.println(comando);
			p.start();
			Map<String, String> entorno = p.environment();
			System.out.println(entorno);	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
