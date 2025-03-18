package edu.dam2.psp.eva1.ejercicios.extremos;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculadorMayorMenor
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        ArrayList<Integer> listaDeNumeros = new ArrayList<Integer>();
        while (true)
        {
            int input = escaner.nextInt();
            if (input == 0)
            {
                break;
            }
            listaDeNumeros.add(input);
        }
        if (!listaDeNumeros.isEmpty())
        {
            int menor = Integer.MAX_VALUE;
            int mayor = Integer.MIN_VALUE;
            for (Integer numero : listaDeNumeros)
            {
                int valor = numero.intValue();
                menor = (valor < menor) ? valor : menor;
                mayor = (valor > mayor) ? valor : mayor;
            }
            System.out.println(menor);
            System.out.println(mayor);
        }
        escaner.close();
    }
}
