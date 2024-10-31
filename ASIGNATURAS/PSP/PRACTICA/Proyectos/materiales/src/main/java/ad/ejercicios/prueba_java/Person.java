package ad.ejercicios.prueba_java;

import java.util.ArrayList;

/**
 * Clase abstracta Person que representa a una persona con nombre, edad, especialidad (major) y asignaturas.
 * Esta clase debe ser extendida por clases específicas que implementen el método printDetails.
 */
abstract class Person
{
    private FullName name;
    private Age age;
    
    /**
     * Campo que representa la especialidad o carrera de la persona.
     * Por implementar clases específicas que utilicen este campo.
     */
    private String major;
    
    /**
     * Lista de asignaturas de la persona.
     * Pendiente de reemplazar tras la implementación completa de la clase Subject.
     */
    private ArrayList<String> subjects;

    /**
     * Constructor que inicializa el nombre, la edad y la especialidad de la persona.
     * @param name el nombre completo de la persona en una sola cadena, separado por espacios.
     * @param age la edad de la persona.
     * @param major la especialidad o carrera de la persona.
     */
    public Person(String name, int age, String major)
    {
        this.name = FullName.parseFullName(name.split(" "));
        this.age = Age.parseAge(age);
        this.major = major;
        subjects = new ArrayList<>();
    }

    /**
     * Devuelve el nombre completo de la persona.
     * @return el nombre de la persona.
     */
    public FullName getName()
    {
        return name;
    }

    /**
     * Devuelve la edad de la persona.
     * @return la edad.
     */
    public Age getAge()
    {
        return age;
    }

    /**
     * Devuelve la especialidad o carrera de la persona.
     * @return la especialidad o carrera.
     */
    public String getMajor()
    {
        return major;
    }

    /**
     * Devuelve una cadena con todas las asignaturas separadas por coma.
     * Pendiente de reemplazo tras la implementación de la clase Subject.
     * @return una cadena con las asignaturas separadas por coma.
     */
    public String getSubjects()
    {
        String subjectsStr = "";
        for (String subject : this.subjects)
        {
            subjectsStr += subject + ", ";
        }
        subjectsStr = subjectsStr.substring(0, subjectsStr.length() - 2);
        return subjectsStr;
    }

    /**
     * Establece un nuevo nombre para la persona.
     * @param name el nuevo nombre completo.
     */
    public void setName(FullName name)
    {
        this.name = name;
    }

    /**
     * Establece una nueva edad para la persona.
     * @param age la nueva edad.
     */
    public void setAge(int age)
    {
        this.age = Age.parseAge(age);
    }

    /**
     * Establece una nueva especialidad para la persona.
     * @param major la nueva especialidad.
     */
    public void setMajor(String major)
    {
        this.major = major;
    }

    /**
     * Agrega una asignatura a la lista de asignaturas si no está ya presente.
     * Pendiente de reemplazo tras la implementación de la clase Subject.
     * @param subject la asignatura a agregar.
     * @return true si se agregó la asignatura; de lo contrario, false.
     */
    public boolean addSubject(String subject)
    {
        if (hasSubject(subject))
        {
            return false;
        }
        subjects.add(subject);
        return true;
    }
    
    /**
     * Elimina una asignatura de la lista de asignaturas de la persona.
     * @param subject la asignatura a eliminar.
     * @return true si se eliminó la asignatura; de lo contrario, false.
     */
    public boolean removeSubject(String subject)
    {
        return subjects.remove(subject);
    }

    /**
     * Compara si otra instancia de Person tiene el mismo nombre que esta instancia.
     * @param p la otra persona a comparar.
     * @return true si ambos tienen el mismo nombre; de lo contrario, false.
     */
    public boolean sameName(Person p)
    {
        return this.name.equals(p.name);
    }

    /**
     * Compara si otra instancia de Person tiene la misma edad que esta instancia.
     * @param p la otra persona a comparar.
     * @return true si ambos tienen la misma edad; de lo contrario, false.
     */
    public boolean sameAge(Person p)
    {
        return this.age.equals(p.age);
    }

    /**
     * Compara si otra instancia de Person tiene la misma especialidad que esta instancia.
     * @param p la otra persona a comparar.
     * @return true si ambos tienen la misma especialidad; de lo contrario, false.
     */
    public boolean sameMajor(Person p)
    {
        return this.major.equals(p.major);
    }

    /**
     * Verifica si la lista de asignaturas de la persona contiene una asignatura específica.
     * Pendiente de reemplazo tras la implementación de la clase Subject.
     * @param comparedSubject la asignatura a buscar.
     * @return true si la asignatura está en la lista; de lo contrario, false.
     */
    public boolean hasSubject(String comparedSubject)
    {
        for (String subject : subjects)
        {
            if (subject.equals(comparedSubject))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Método abstracto para imprimir los detalles de la persona, que debe ser implementado en clases derivadas.
     */
    public abstract void printDetails();
}
