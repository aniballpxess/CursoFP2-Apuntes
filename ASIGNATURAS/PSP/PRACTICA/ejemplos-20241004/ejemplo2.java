package ejemplos;
import java.io.IOException;
public class ejemplo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length <=0){
			System.err.println("Se necesita un programa a ejecutar");
			System.exit(-1);
		}
		Runtime runtime = Runtime.getRuntime();
		try
		{
			Process proceso = runtime.exec(args);
			proceso.destroy();
			
		}
		catch( IOException ex){	
			System.err.println("Error");
			System.exit(-1);
		}
	}

}
