package psp.ejercicios.media;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculadorMedia
{
    public static void main(String[] args)
    {
        Scanner escaner = new Scanner(System.in);
        ArrayList<Integer> listaDeNumeros = new ArrayList<Integer>();
        int sumatorio = 0;
        while (true)
        {
            int input = escaner.nextInt();
            if (input == 0)
            {
                break;
            }
            listaDeNumeros.add(input);
        }
        for (Integer numero : listaDeNumeros)
        {
            sumatorio = sumatorio + numero.intValue();
        }
        System.out.println(sumatorio / listaDeNumeros.size());
        escaner.close();
    }
}
