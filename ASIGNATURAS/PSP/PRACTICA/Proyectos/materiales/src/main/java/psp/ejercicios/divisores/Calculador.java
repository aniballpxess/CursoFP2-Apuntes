package psp.ejercicios.divisores;

public class Calculador
{
    /**
     * Esta función calcula todos los divisores de un entero que recibe, tanto
     * positivos como negativos.
     * 
     * <p>
     * Es importante destacar que la función lanzará {@link ArithmeticException} si
     * el parametro introducido es {@link Integer#MIN_VALUE} debido al rango
     * asimétrico de representación de los {@code int}.
     * 
     * <p>
     * PENDIENTE: Eliminar lanzamiento de {@link ArithmeticException}.
     * 
     * @param n el entero cuyos divisores se han de calcular
     * @return el número de divisores que el parámetro {@code n} tiene
     * @throws ArithmeticException si el parámetro es {@link Integer#MIN_VALUE}
     */
    public static int cuantosDivisores(int n)
    {
        n = Math.absExact(n);
        if (n == 0)
        {
            return 0;
        }
        if (n == 1)
        {
            return 2;
        }
        boolean posiblePrimo = true;
        int divisores = 2;
        int divisorDePrueba = 2;
        int limite = 0;
        while (true)
        {
            if (posiblePrimo)
            {
                limite = n / divisorDePrueba;
            }
            if (limite < divisorDePrueba)
            {
                break;
            }
            if (n % divisorDePrueba == 0)
            {
                divisores++;
                posiblePrimo = false;
            }
            divisorDePrueba++;
        }
        return divisores * 2;
    }
}
