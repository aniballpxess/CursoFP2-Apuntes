package ad.ejercicios.prueba_java;

class Teacher extends Person
{
    private Double salary;

    public Teacher(String fullName, int age, String major, Double salary)
    {
        super(fullName, age, major);
        this.salary = salary;
    }

    public Teacher(String fullName, int age, String major)
    {
        super(fullName, age, major);
        this.salary = 0.0;
    }
    
    public Double getSalary()
    {
        return salary;
    }

    @Override
    public void printDetails()
    {
        System.out.println("INFO PROFESOR");
        System.out.println("Nombre: Don " + getName());
        System.out.println("Edad: " + getAge());
        System.out.println("Carrera: " + getMajor());
        System.out.println("Salario: " + salary);
    }

}