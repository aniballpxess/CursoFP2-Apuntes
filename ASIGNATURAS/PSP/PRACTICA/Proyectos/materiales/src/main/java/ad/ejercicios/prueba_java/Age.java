package ad.ejercicios.prueba_java;


public class Age {
    private int age;

    public Age(int age)
    {
        setAge(age);
    }

    public void setAge(int age)
    {
        if (age < 0 || 120 < age) {
            throw new NumberFormatException();
        }
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    @Override
    public String toString()
    {
        return age + " años";
    }
}
