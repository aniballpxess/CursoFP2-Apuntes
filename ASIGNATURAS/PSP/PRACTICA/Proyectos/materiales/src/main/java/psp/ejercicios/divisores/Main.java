package psp.ejercicios.divisores;

public class Main 
{
    public static void main(String[] args) 
    {
        if (args.length == 0)
        {
            System.out.println("No has pasado ningún argumento.");
            System.exit(-20);
        }
        if (args.length != 1)
        {
            System.out.println("Este programa solo admite 1 argumento.");
            System.exit(-21);
        }
        try
        {
            int num = Integer.parseInt(args[0]);
            System.out.println(Calculador.cuantosDivisores(num));
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("El argumento pasado no es un número entero.");
            System.exit(-30);
        }
    }
}
