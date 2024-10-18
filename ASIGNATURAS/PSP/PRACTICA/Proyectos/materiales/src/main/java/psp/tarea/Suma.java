package psp.tarea;

import java.util.Scanner;

public class Suma
{
    public static void main(String[] args)
    {
        try (Scanner escaner = new Scanner(System.in))
        {
            int a = Integer.parseInt(escaner.nextLine());
            int b = Integer.parseInt(escaner.nextLine());
            System.out.println(a + b);
        }
        catch (Exception e)
        {
            System.err.println(e.getStackTrace());
        }
    }
}
