package psp.ejercicios.mayusculas;

import java.util.Scanner;

public class Conversor
{
    public static void main(String[] args)
    {
        try (Scanner escaner = new Scanner(System.in))
        {
            while (true)
            {
                String input = escaner.nextLine();
                System.out.println(input.toUpperCase());
            }
        }
    }
}
