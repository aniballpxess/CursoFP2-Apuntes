package ejemplos;

import java.util.Scanner;

public class Doble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Este proceso calcula el doble de un n�mero. El n�mero lo vamos a pasar a trav�s de los atributos del main
		if(args.length==0) {
			System.out.println("No se han pasado par�metros");
		}
		int num, doble=0;
		num=Integer.parseInt(args[0]);
		doble=num*2;
		System.out.println(num+"*2="+doble);
		//System.out.println(doble);
	}

}
