package ad.ejercicios.prueba_java;

public class FullName
{
    private static boolean validNamePart(String namePart)
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

    private String name;
    private String firstSurname;
    private String secondSurname;

    private FullName(String[] fullName)
    {
        name = fullName[0];
        firstSurname = fullName[1];
        secondSurname = fullName[2];
    }

    public String getName()
    {
        return name;
    }

    public String getFirstSurname()
    {
        return firstSurname;
    }

    public String getSecondSurname()
    {
        return secondSurname;
    }

    public boolean setName(String name)
    {
        if (validNamePart(name))
        {
            this.name = name;
            return true;
        }
        return false;
    }

    public boolean setFirstSurname(String firstSurname)
    {
        if (validNamePart(firstSurname))
        {
            this.firstSurname = firstSurname;
            return true;
        }
        return false;
    }

    public boolean setSecondSurname(String secondSurname)
    {
        if (validNamePart(secondSurname))
        {
            this.secondSurname = secondSurname;
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return name + " " + firstSurname + " " + secondSurname;
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.toString().equals(obj.toString());
    }
}
