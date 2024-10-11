package psp.ejercicios.sumatorio;

public class Sumador
{
    /**
     * Simple sumatorio de un intervalo de numeros enteros. Los límites tienen que
     * ser DIFERENTES y ser pasados en el orden correcto o lanzará una excepción.
     * 
     * @param limiteInf - límite inferior del intervalo
     * @param limiteSup - limite superior del intervalo
     * @return - el sumatorio de todos los enteros dentro del intervalo
     * @throws IllegalArgumentException
     */
    public static int sumatorioDeIntervalo(int limiteInf, int limiteSup)
    {
        if (!(limiteInf < limiteSup))
        {
            throw new IllegalArgumentException();
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
