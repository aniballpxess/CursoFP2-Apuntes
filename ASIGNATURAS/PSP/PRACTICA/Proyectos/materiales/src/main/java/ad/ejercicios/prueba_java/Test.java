package ad.ejercicios.prueba_java;

// Clase principal que demuestra los principios de POO
public class Test
{
    public static void main(String[] args)
    {
        // Creando objetos de las clases Student y Teacher
        Person student = new Teacher("Alice", 20, "Computer Science");
        Person teacher = new Student("Mr. Smith", 45, "Mathematics");

        // Usando polimorfismo para llamar a printDetails() en diferentes objetos
        student.printDetails();
        teacher.printDetails();
    }
}
