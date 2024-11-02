package edu.dam2.ad.tareas.prueba_java;

/**
 * La clase Student representa a un estudiante, que es una extensión de la clase
 * Person. Un estudiante tiene un nombre completo, edad, especialidad (major) y
 * créditos acumulados.
 */
class Student extends Person
{
    private Integer credits;

    /**
     * Constructor que inicializa un estudiante con nombre completo, edad,
     * especialidad y créditos.
     * 
     * @param fullName el nombre completo del estudiante.
     * @param age      la edad del estudiante.
     * @param major    la especialidad o carrera del estudiante.
     * @param credits  los créditos acumulados por el estudiante.
     * @throws IllegalArgumentException si algunos de los parametros pasados no es
     *                                  válido.
     */
    public Student(String fullName, int age, String major, Integer credits) throws IllegalArgumentException
    {
        super(fullName, age, major);
        this.credits = credits;
    }

    /**
     * Constructor que inicializa un estudiante con nombre completo, edad y
     * especialidad, estableciendo los créditos acumulados en 0.
     * 
     * @param fullName el nombre completo del estudiante.
     * @param age      la edad del estudiante.
     * @param major    la especialidad o carrera del estudiante.
     * @throws IllegalArgumentException si algunos de los parametros pasados no es
     *                                  válido.
     */
    public Student(String fullName, int age, String major) throws IllegalArgumentException
    {
        super(fullName, age, major);
        this.credits = 0;
    }

    /**
     * Devuelve los créditos acumulados del estudiante.
     * 
     * @return los créditos acumulados.
     */
    public Integer getCredits()
    {
        return credits;
    }

    /**
     * Establece los créditos acumulados del estudiante.
     * 
     * @param credits los nuevos créditos acumulados a establecer.
     */
    public void setCredits(Integer credits)
    {
        this.credits = credits;
    }

    /**
     * Imprime los detalles del estudiante, incluyendo nombre, edad, especialidad,
     * materias optadas y créditos acumulados.
     */
    @Override
    public void printDetails()
    {
        System.out.println("INFO ESTUDIANTE");
        System.out.println("Nombre: " + super.getName());
        System.out.println("Edad: " + super.getAge());
        System.out.println("Carrera: " + super.getMajor());
        System.out.println("Materias optadas: " + super.getSubjects());
        System.out.println("Creditos: " + credits);
    }
}
