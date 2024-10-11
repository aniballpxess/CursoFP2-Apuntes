package ejemplos;

import java.io.IOException;



public class RunProcessRT {
	public static void main(String[] args) {
		if(args.length==0) {
			System.out.println("Se necesita un programa a ejecutar");
			System.exit(-1);
		}
		
		try {
			String[] valores= {args[0], args[1]};
			Process proceso=Runtime.getRuntime().exec(valores[0]);
			Process proceso2=Runtime.getRuntime().exec(valores[1]);
			int retorno=proceso.waitFor();
			System.out.println("proceso1:"+proceso);
			//System.out.println("el estado del " +proceso + "es" + retorno);
			//Process proceso2=Runtime.getRuntime().exec(args[2]);
			//Thread.sleep(1000);
			//proceso2.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

