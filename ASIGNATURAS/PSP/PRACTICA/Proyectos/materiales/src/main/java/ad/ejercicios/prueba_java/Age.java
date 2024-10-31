package ad.ejercicios.prueba_java;

/**
 * La clase Age representa una edad que está dentro de un rango específico (5 a 120 años).
 * Permite validar, establecer y obtener una edad en el rango permitido.
 */
public class Age
{
    /**
     * Valida si una edad dada está en el rango permitido.
     * @param age la edad a validar.
     * @return true si la edad está entre 5 y 120; de lo contrario, false.
     */
    private static boolean validAge(int age)
    {
        return 5 < age && age < 120;
    }

    /**
     * Crea una instancia de Age si la edad es válida.
     * @param age la edad para crear la instancia.
     * @return una instancia de Age si la edad es válida.
     * @throws NumberFormatException si la edad es inválida.
     */
    public static Age parseAge(int age)
    {
        if (!validAge(age))
        {
            throw new NumberFormatException();
        }
        return new Age(age);
    }

    private int age;

    /**
     * Constructor privado que inicializa la edad.
     * @param age la edad a inicializar.
     */
    private Age(int age)
    {
        this.age = age;
    }

    /**
     * Devuelve la edad actual.
     * @return la edad.
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Establece una nueva edad si es válida.
     * @param age la nueva edad a establecer.
     * @return true si la edad es válida y se asignó correctamente; de lo contrario, false.
     */
    public boolean setAge(int age)
    {
        if (validAge(age))
        {
            this.age = age;
            return true;
        }
        return false;
    }

    /**
     * Devuelve una representación en cadena de la edad.
     * @return una cadena que representa la edad en años.
     */
    @Override
    public String toString()
    {
        return age + " años";
    }

    /**
     * Compara si dos objetos son iguales basándose en la representación en cadena de la edad.
     * @param obj el objeto a comparar.
     * @return true si ambos objetos tienen la misma representación en cadena; de lo contrario, false.
     */
    @Override
    public boolean equals(Object obj)
    {
        return this.toString().equals(obj.toString());
    }
}
