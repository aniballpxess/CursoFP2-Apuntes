package edu.dam2.psp.eva1.ejercicios.doble;

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
