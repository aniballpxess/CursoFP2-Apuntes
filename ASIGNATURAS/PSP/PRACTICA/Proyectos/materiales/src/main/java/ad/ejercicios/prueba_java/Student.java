package ad.ejercicios.prueba_java;

class Student extends Person
{
    private Integer credits;

    public Student(String[] name, Integer age, String major, Integer credits)
    {
        super(name, age, major); 
        this.credits = credits;
    }

    public Student(String[] name, Integer age, String major)
    {
        super(name, age, major); 
        this.credits = 0;
    }

    public Integer getCredits()
    {
        return credits;
    }

    @Override
    public void printDetails()
    {
        System.out.println("INFO");
        System.out.println("Nombre: " + getName());
        System.out.println("Edad: " + getAge());
        System.out.println("Curso: " + getMajor());
        System.out.println("Creditos: " + credits);
    }
}