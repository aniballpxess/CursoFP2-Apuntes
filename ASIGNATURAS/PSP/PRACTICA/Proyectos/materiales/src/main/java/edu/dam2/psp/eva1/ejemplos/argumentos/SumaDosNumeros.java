package edu.dam2.psp.eva1.ejemplos.argumentos;

public class SumaDosNumeros
{
    public static void main(String[] args)
    {
        boolean sonEnteros = args[0].matches("[+-]?\\d+") && args[1].matches("[+-]?\\d+");
        try
        {
            if (args.length == 0)
                throw new IllegalArgumentException("No has pasado ningún argumento.");

            if (args.length < 2)
                throw new IllegalArgumentException("Este programa necesita 2 argumentos.");

            if (args.length > 2)
                throw new IllegalArgumentException("Este programa solo permite 2 argumentos.");

            if (!sonEnteros)
                throw new NumberFormatException("Alguno de los argumentos pasados no es un número entero.");

            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            System.out.println("La suma es: " + (num1 + num2));
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
