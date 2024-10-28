package ad.ejercicios.prueba_java;

public class FullName
{
    private String name;
    private String firstSurname;
    private String secondSurname;

    private FullName(String[] fullName)
    {
        name = fullName[0];
        firstSurname = fullName[1];
        secondSurname = fullName[2];
    }

    protected static boolean validNamePart(String namePart)
    {
        return namePart.matches("[A-Za-záéíóúÁÉÍÓÚñÑüÜ]+");
    }

    public static FullName parseFullName(String... name)
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

    @Override
    public String toString()
    {
        return name + " " + firstSurname + " " + secondSurname;
    }
}
