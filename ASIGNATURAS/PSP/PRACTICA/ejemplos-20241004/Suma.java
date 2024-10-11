package ejemplos;

public class Suma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length==0)
		{
				System.out.println("no hay parámetros");
		}
		int num1, num2, suma;
		num1= Integer.parseInt(args[0]);
		num2= Integer.parseInt(args[1]);
		suma= num1+num2;
		System.out.println("La suma es :" +suma);

	}

}
