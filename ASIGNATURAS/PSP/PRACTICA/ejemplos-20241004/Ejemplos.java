package ejemplos;

import java.io.IOException;

public class Ejemplos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Lanzo bloc de notas
			String[]infoProceso={"Notepad.exe","C:\\Users\\laura\\OneDrive\\Escritorio\\sol pseudocodigo.txt"};
			Process proceso=Runtime.getRuntime().exec(infoProceso);
			//Lanzo paint
			Process proceso2=Runtime.getRuntime().exec("mspaint.exe");
			
			int codigoRetorno=proceso.waitFor();
			//System.out.println("Fin de la ejecuci�n:"+codigoRetorno);
			System.out.println(proceso);
			System.out.println(proceso2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
