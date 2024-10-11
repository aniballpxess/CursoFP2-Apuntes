package ejemplos;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Pruebas {

	public static void main(String args[]) throws IOException	{
	
		// EJEMPLO NOTEPAD
		//ruta al notepad
		/*String ruta="C:\\windows\\notepad.exe";
		
		ProcessBuilder pb = new ProcessBuilder(ruta); 
		pb.start();*/
		
		///////////////////////////////
		//EJEMPLOS DOC PRUEBAS CON PB
		///////////////////////////////
		
		//EJEMPLO 1
		//ProcessBuilder pb= new ProcessBuilder("CMD","/C","DIR");
		//pb.start();
		
		//EJEMPLO 2
		//ProcessBuilder pb= new ProcessBuilder("CMD","/C","DIR");
		//pb.inheritIO();
		//pb.start();
		
		//EJEMPLO 3
		/*ProcessBuilder pb= new ProcessBuilder("CMD","/C","DIR");
		pb.inheritIO();
		File directorio= pb.directory();
		pb.start();
		System.out.println(directorio);*/
		
		//EJEMPLO 4 
		/*ProcessBuilder pb= new ProcessBuilder("CMD","/C","DIR");
		pb.inheritIO();
		File directorio= pb.directory(); //Si lo pongo aqui no funciona por estar antes de decir C:\\
		pb.directory(new File ("C:\\") );
		File directorio1= pb.directory();
		pb.start();
		System.out.println("El directorio es " + directorio1);*/
		
		//EJEMPLO 5 
		/*ProcessBuilder pb= new ProcessBuilder("CMD","/C","DOR");
		pb.inheritIO();
		File directorio= pb.directory(); //Si lo pongo aqui no funciona por estar antes de decir C:\\
		pb.directory(new File ("C:\\") );
		directorio= pb.directory();
		String[] archivos = directorio.list();
		for (String nombre : archivos) {
	        System.out.println(nombre);
		}
		
		pb.start();
		System.out.println("El directorio es " + directorio);*/
		
		
		//EJEMPLO 6
		//ProcessBuilder pb= new ProcessBuilder("CMD","/C","DOR");
		//si lo pruebo con DOR escribe en fichero, si lo pruebo con DIR en pantalla 
		//pb.inheritIO();
		//File directorio= pb.directory(); Si lo pongo aqui no funciona por estar antes de decir C:\\
		//pb.directory(new File ("C:\\") );
		//File directorio= pb.directory();
		//ha de existir el directorio, el fichero lo crea
		//pb.redirectError(new File ("c:\\temp\\error.txt")); 
		//pb.start();
		//System.out.println("El directorio es " + directorio);

		
		//EJEMPLO 7
		/*ProcessBuilder pb= new ProcessBuilder("CMD","/C","DIR");
		//si lo pruebo con DOR escribe en fichero, si lo pruebo con DIR en pantalla 
		//Si inheritIO estuviera despu�s del redirectOutpt escribiria en la consola
		//al estar antes esta instrucci�n queda inutilizada m�s redirectOtput
		pb.inheritIO();
		pb.directory(new File ("C:\\") );
		File directorio= pb.directory();
		//ha de existir el directorio, el fichero lo crea
		pb.redirectOutput(new File("C:\\Windows\\temp\\salida.txt"));
		//como hay error en CMD (DOR) no escribe en fichero salida, solo obtengo error.txt
		pb.redirectError(new File ("C:\\\\Windows\\\\temp\\\\error.txt")); 
		pb.start();
		System.out.println("El directorio es " + directorio);*/
				
		//EJEMPLO 8
		//ProcessBuilder pb= new ProcessBuilder("CMD","/C","DIR");
		//pb.inheritIO();
		//pb.directory(new File ("C:\\") );
		//File directorio= pb.directory();
		//ha de existir el directorio, el fichero lo crea
		//pb.redirectOutput(new File("c:\\temp\\salida.txt"));
		//como no hay error en CMD (DIR) escribe en fichero salida
		//pb.redirectError(new File ("c:\\temp\\error.txt")); 
		//pb.start();
		//System.out.println("El directorio es " + directorio);
				
		//EJEMPLO 9
		//obtengo DIR de C:\\Windows sin cambiar de directorio 
		//ProcessBuilder pb= new ProcessBuilder("CMD","/C","Dir","C:\\Windows");
		//pb.inheritIO(); //saco por pantalla
		//pb.directory(new File ("C:\\") );
		//File directorio= pb.directory();
		//a pesar de iondicar c:\\ como directorio de trabajo el DIR lo ejecuto sobre C:\\Windows
		//pb.start();
		//System.out.println("El directorio es " + directorio);
		
		//EJEMPLO 10
		//ProcessBuilder pb= new ProcessBuilder("CMD","/C","Dir","C:\\Windows");
		//pb.inheritIO(); //saco por pantalla
		//pb.directory(new File ("C:\\") );
		//File directorio= pb.directory();
		//a pesar de iondicar c:\\ como directorio de trabajo el DIR lo ejecuto sobre C:\\Windows
		//pb.start();
		//System.out.println("El directorio es " + directorio);
		//List<String> comando = pb.command(); //argumentos pasados a metodo processBuilder 
		//System.out.println("Argumentos ProcessBouilder: "+ comando); //imprimo por consola
		
		//EJEMPLO 11
		
		//Map<String, String> entorno = pb.environment();
		//java.util.Map<String, String> entorno = pb.environment();
		//System.out.println(entorno);

		//EJEMPLO 12 B no existe fichero nominas.txt
		//copy es un comando DOS
		//ProcessBuilder pb = new ProcessBuilder("CMD","/C","copy","c:\\temp\\nominas.txt", "c:\\temp\\nominas2.txt");
		//pb.redirectError(new File ("c:\\temp\\errornominas.txt"));
		//File directorio= pb.directory();
		//pb.start();
		//System.out.println("El directorio es " + directorio); //vemos que el directorio es null
		
		//EJEMPLO 12A existe fichero
		//ProcessBuilder pb = new ProcessBuilder("CMD","/C","copy","c:\\temp\\nominas.txt", "c:\\temp\\nominas2.txt");
		//pb.redirectError(new File ("c:\\temp\\errornominas.txt"));
		//pb.start();

		
		
		//EJEMPLO 13A L�nzalo sin que exista nominas.txt �Qu� ha pasado?
		//ProcessBuilder pb = new ProcessBuilder("CMD","/C","copy","c:\\temp\\nominas.txt", "c:\\temp\\nominas2.txt");
		//pb.directory(new File ("C:\\temp") ); //establezco directorio
		//pb.redirectOutput(new File("C:\\temp\\nominasresultado.txt"));
		//pb.redirectError(new File ("c:\\temp\\errornominas.txt"));
		//pb.start();
		//errornominas se ha creado sin contenido, ya que no hay error en la ejecuci�n del PB
		//nominasresultado nos da el restuldao de ejecutar el PB
		//y nos dice que no hay fichero nominas que copiar
		
		//EJEMPLO 13B L�nzalo existiendo nominas.txt �Qu� ha pasado?
		/*ProcessBuilder pb = new ProcessBuilder("CMD","/C","copy","c:\\temp\\nominas.txt", "c:\\temp\\nominas2.txt");
		pb.directory(new File ("C:\\temp") ); //establezco directorio
		pb.redirectOutput(new File("C:\\temp\\nominasresultado.txt"));
		pb.redirectError(new File ("c:\\temp\\errornominas.txt"));
		pb.start();*/



		

		

		
		
		System.out.println("Terminado");
		
	}
	

}