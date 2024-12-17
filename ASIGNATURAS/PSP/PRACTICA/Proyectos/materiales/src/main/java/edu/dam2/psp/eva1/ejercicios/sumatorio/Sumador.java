package edu.dam2.psp.eva1.ejercicios.sumatorio;

public class Sumador
{
    /**
     * <p>Simple sumatorio de un intervalo de numeros enteros. A pesar de la implicación de los nombres @param limiteInf y @param limiteSup , no importa el orden de los parametros.
     * 
     * @param limiteInf - límite inferior del intervalo
     * @param limiteSup - limite superior del intervalo
     * @return - el sumatorio de todos los enteros dentro del intervalo
     */
    public static int sumatorioDeIntervalo(int limiteInf, int limiteSup)
    {
        if (limiteInf == limiteSup)
        {
            return limiteInf;
        }
        if (limiteInf > limiteSup)
        {
            limiteInf = limiteInf ^ limiteSup;
            limiteSup = limiteSup ^ limiteInf;
            limiteInf = limiteInf ^ limiteSup;
        }
        int suma = limiteInf;
        while (true)
        {
            limiteInf++;
            suma = suma + limiteInf;
            if (limiteInf == limiteSup)
            {
                break;
            }
        }
        return suma;
    }
}
