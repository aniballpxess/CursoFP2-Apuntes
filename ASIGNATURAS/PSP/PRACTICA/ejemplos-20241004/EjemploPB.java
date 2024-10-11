package ejemplos;

import java.io.IOException;

public class EjemploPB {

	public static void main(String[] args) {
//		//ProcessBuilder pb=new ProcessBuilder("notepad.exe", "notas.txt");
//		try {
//			//Process proceso=pb.start();
//			Process proceso=new ProcessBuilder("notepad.exe", "notas.txt").start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ProcessBuilder pBuilder=new ProcessBuilder(args);
		for(int i=0;i<3;i++){
			try {
				pBuilder.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
