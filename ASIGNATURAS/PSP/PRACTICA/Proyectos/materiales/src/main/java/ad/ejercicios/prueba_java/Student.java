package ad.ejercicios.prueba_java;

class Student extends Person
{
    private Integer credits;

    public Student(String fullName, int age, String major, Integer credits)
    {
        super(fullName, age, major); 
        this.credits = credits;
    }

    public Student(String fullName, int age, String major)
    {
        super(fullName, age, major); 
        this.credits = 0;
    }

    public Integer getCredits()
    {
        return credits;
    }

    @Override
    public void printDetails()
    {
        System.out.println("INFO ESTUDIANTE");
        System.out.println("Nombre: " + getName());
        System.out.println("Edad: " + getAge());
        System.out.println("Carrera: " + getMajor());
        System.out.println("Creditos: " + credits);
    }
}