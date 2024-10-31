package edu.dam2.ad.tareas.prueba_java;

/**
 * La clase FullName representa un nombre completo que consta de un nombre, primer apellido y segundo apellido.
 * Permite validar, establecer y obtener cada parte del nombre.
 */
public class FullName
{
    /**
     * Valida si una parte del nombre (nombre o apellido) contiene solo letras y caracteres de acentuación española.
     * @param namePart la parte del nombre a validar.
     * @return true si la parte del nombre es válida; de lo contrario, false.
     */
    private static boolean validNamePart(String namePart)
    {
        return namePart.matches("[A-Za-záéíóúÁÉÍÓÚñÑüÜ]+");
    }

    /**
     * Crea una instancia de FullName a partir de un arreglo de tres cadenas (nombre, primer apellido, segundo apellido).
     * @param name arreglo de tres cadenas que representa el nombre completo.
     * @return una instancia de FullName si las partes del nombre son válidas.
     * @throws IllegalArgumentException si no se pasan exactamente tres elementos o si alguna parte no es válida.
     */
    public static FullName parseFullName(String... name) throws IllegalArgumentException
    {
        if (name.length != 3)
        {
            throw new IllegalArgumentException();
        }
        for (String namePart : name)
        {
            if (!validNamePart(namePart))
            {
                throw new IllegalArgumentException();
            }
        }
        return new FullName(name);
    }

    private String name;
    private String firstSurname;
    private String secondSurname;

    /**
     * Constructor privado que inicializa el nombre, primer apellido y segundo apellido.
     * @param fullName un arreglo de tres elementos que representan el nombre completo.
     */
    private FullName(String[] fullName)
    {
        name = fullName[0];
        firstSurname = fullName[1];
        secondSurname = fullName[2];
    }

    /**
     * Devuelve el nombre.
     * @return el nombre.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Devuelve el primer apellido.
     * @return el primer apellido.
     */
    public String getFirstSurname()
    {
        return firstSurname;
    }

    /**
     * Devuelve el segundo apellido.
     * @return el segundo apellido.
     */
    public String getSecondSurname()
    {
        return secondSurname;
    }

    /**
     * Establece el nombre si es válido.
     * @param name el nuevo nombre a establecer.
     * @return true si el nombre es válido y se asignó correctamente; de lo contrario, false.
     */
    public boolean setName(String name)
    {
        if (validNamePart(name))
        {
            this.name = name;
            return true;
        }
        return false;
    }

    /**
     * Establece el primer apellido si es válido.
     * @param firstSurname el nuevo primer apellido a establecer.
     * @return true si el primer apellido es válido y se asignó correctamente; de lo contrario, false.
     */
    public boolean setFirstSurname(String firstSurname)
    {
        if (validNamePart(firstSurname))
        {
            this.firstSurname = firstSurname;
            return true;
        }
        return false;
    }

    /**
     * Establece el segundo apellido si es válido.
     * @param secondSurname el nuevo segundo apellido a establecer.
     * @return true si el segundo apellido es válido y se asignó correctamente; de lo contrario, false.
     */
    public boolean setSecondSurname(String secondSurname)
    {
        if (validNamePart(secondSurname))
        {
            this.secondSurname = secondSurname;
            return true;
        }
        return false;
    }

    /**
     * Devuelve una representación en cadena del nombre completo.
     * @return una cadena que representa el nombre completo.
     */
    @Override
    public String toString()
    {
        return name + " " + firstSurname + " " + secondSurname;
    }

    /**
     * Compara si dos objetos FullName son iguales basándose en su representación en cadena.
     * @param obj el objeto a comparar.
     * @return true si ambos objetos tienen la misma representación en cadena; de lo contrario, false.
     */
    @Override
    public boolean equals(Object obj)
    {
        return this.toString().equals(obj.toString());
    }
}
