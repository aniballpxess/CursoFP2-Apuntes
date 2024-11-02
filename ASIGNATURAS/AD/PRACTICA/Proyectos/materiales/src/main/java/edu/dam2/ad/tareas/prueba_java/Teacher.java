package edu.dam2.ad.tareas.prueba_java;

/**
 * La clase Teacher representa a un profesor, que es una extensión de la clase
 * Person. Un profesor tiene un nombre completo, edad, especialidad (major) y
 * salario.
 */
class Teacher extends Person
{
    private Double salary;

    /**
     * Constructor que inicializa un profesor con nombre completo, edad,
     * especialidad y salario.
     * 
     * @param fullName el nombre completo del profesor.
     * @param age      la edad del profesor.
     * @param major    la especialidad o carrera del profesor.
     * @param salary   el salario del profesor.
     * @throws IllegalArgumentException si algunos de los parametros pasados no es
     *                                  válido.
     */
    public Teacher(String fullName, int age, String major, Double salary) throws IllegalArgumentException
    {
        super(fullName, age, major);
        this.salary = salary;
    }

    /**
     * Constructor que inicializa un profesor con nombre completo, edad y
     * especialidad, estableciendo el salario en 0.0.
     * 
     * @param fullName el nombre completo del profesor.
     * @param age      la edad del profesor.
     * @param major    la especialidad o carrera del profesor.
     * @throws IllegalArgumentException si algunos de los parametros pasados no es
     *                                  válido.
     */
    public Teacher(String fullName, int age, String major) throws IllegalArgumentException
    {
        super(fullName, age, major);
        this.salary = 0.0;
    }

    /**
     * Devuelve el salario del profesor.
     * 
     * @return el salario.
     */
    public Double getSalary()
    {
        return salary;
    }

    /**
     * Establece el salario del profesor.
     * 
     * @param salary el nuevo salario a establecer.
     */
    public void setSalary(Double salary)
    {
        this.salary = salary;
    }

    /**
     * Imprime los detalles del profesor, incluyendo nombre, edad, especialidad,
     * materias impartidas y salario.
     */
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
