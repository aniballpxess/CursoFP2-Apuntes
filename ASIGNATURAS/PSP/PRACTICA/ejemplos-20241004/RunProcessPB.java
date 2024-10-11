package ejemplos;

import java.io.IOException;
import java.util.Arrays;

public class RunProcessPB {
	//En este ejemplo, voy a pasar por par�metros del main(args) los valores notepad.exe mspaint.exe
	public static void main(String[] args) throws IOException, InterruptedException {
		//ejecuto notepad
		//Process proceso=pb.start();
		//Process proceso2=pb2.start();
		 /*ProcessBuilder pBuilder=new ProcessBuilder("Notepad.exe", "datos.txt");
		 
		java.util.Map<String, String> env=pBuilder.environment();
		Process proceso=pBuilder.start();
		 System.out.println("Número de procesadores:"+env.get("NUMBER_OF_PROCESSORS"));
		
		//hago que el padre espere hasta que el proceso hijo proceso (notepad.exe) termine
		int retorno=proceso.waitFor();
		System.out.println("La ejecuci�n devuelve "+retorno);*/
	}
}
