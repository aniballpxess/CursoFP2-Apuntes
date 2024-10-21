package psp.ejercicios.mayusculas;

import java.util.Scanner;

public class Conversor
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        while (true)
        {
            String input = escaner.nextLine();
            if (input.equals("."))
            {
                break;
            }
            System.out.println(input.toUpperCase());
        }
        escaner.close();
    }
}
