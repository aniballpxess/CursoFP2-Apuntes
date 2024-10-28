package ad.ejercicios.prueba_java;

class Teacher extends Person
{
    private Double salary;

    public Teacher(String[] name, Integer age, String major, Double salary)
    {
        super(name, age, major);
        this.salary = salary;
    }

    public Teacher(String[] name, Integer age, String major)
    {
        super(name, age, major);
        this.salary = 0.0;
    }
    
    public Double getSalary()
    {
        return salary;
    }

    @Override
    public void printDetails()
    {
        System.out.println("Nombre del estudiante: " + getName());
        System.out.println("Edad: " + getAge());
        System.out.println("Salario: " + salary);
    }

}