package psp.ejercicios.doble;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            if (args.length == 0)
                throw new IllegalArgumentException("No has pasado ningún argumento.");

            if (args.length != 1)
                throw new IllegalArgumentException("Este programa solo admite 1 argumento.");

            if (!args[0].matches("[+-]?\\d+"))
                throw new NumberFormatException("El argumento pasado no es un número entero.");

            int num = Integer.parseInt(args[0]);
            System.out.println(num + " x 2 = " + num * 2);
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
