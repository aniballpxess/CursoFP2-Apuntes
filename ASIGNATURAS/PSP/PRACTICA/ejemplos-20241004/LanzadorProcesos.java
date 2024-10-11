package ejemplos;
import java.io.IOException;
public class LanzadorProcesos {

	public void ejecutar(String ruta){
			 ProcessBuilder pb;
			 try {
			 pb = new ProcessBuilder(ruta);
			 pb.start();
			 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
		 }
			 

	public static void main(String[] args) {
		 
		/*if (args.length <= 0) {
			 System.err.println("Se necesita un programa a ejecutar");
			 System.exit(-1);
		 }*/
		//String ruta="C:\\Program Files\\WindowsApps\\Microsoft.WindowsCalculator_11.2405.2.0_x64__8wekyb3d8bbwe\\CalculatorAPP.exe";
		 LanzadorProcesos lp=new LanzadorProcesos();
		 lp.ejecutar(args[0]);
		 System.out.println("Finalizado");
		 }

}
