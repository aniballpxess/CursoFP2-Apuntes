package psp.ejercicios.doble;

import java.util.Scanner;

public class Doblador
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        int num = escaner.nextInt();
        System.out.println(num * 2);
        escaner.close();
    }
}
