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

    public void setSalary(Double salary)
    {
        this.salary = salary;
    }

    @Override
    public void printDetails()
    {
        System.out.println("INFO PROFESOR");
        System.out.println("Nombre: Don " + super.getName());
        System.out.println("Edad: " + super.getAge());
        System.out.println("Carrera: " + super.getMajor());
        System.out.println("Materias impartidas: " + super.getSubjects());
        System.out.println("Salario: " + salary);
    }

}