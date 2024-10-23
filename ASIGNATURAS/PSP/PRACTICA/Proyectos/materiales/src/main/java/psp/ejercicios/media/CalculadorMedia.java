package psp.ejercicios.media;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculadorMedia
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
            int sumatorio = 0;
            for (Integer numero : listaDeNumeros)
            {
                sumatorio = sumatorio + numero.intValue();
            }
            double media = (double) sumatorio / listaDeNumeros.size();
            System.out.println(media);
        }
        escaner.close();
    }
}
