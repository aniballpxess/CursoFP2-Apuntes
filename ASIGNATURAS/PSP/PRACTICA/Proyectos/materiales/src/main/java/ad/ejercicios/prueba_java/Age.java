package ad.ejercicios.prueba_java;

public class Age
{
    private static boolean validAge(int age)
    {
        return 5 < age && age < 120;
    }

    public static Age parseAge(int age)
    {
        if (!validAge(age))
        {
            throw new NumberFormatException();
        }
        return new Age(age);
    }

    private int age;

    private Age(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public boolean setAge(int age)
    {
        if (validAge(age))
        {
            this.age = age;
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return age + " años";
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.toString().equals(obj.toString());
    }
}