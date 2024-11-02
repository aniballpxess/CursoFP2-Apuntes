package edu.dam2.ad.tareas.prueba_java;

import java.util.ArrayList;
import java.util.Scanner;

public class Test
{
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int choice;
        do
        {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add new Student");
            System.out.println("2. Add new Teacher");
            System.out.println("3. Display all Students");
            System.out.println("4. Display all Teachers");
            System.out.println("5. Add Subject to Student");
            System.out.println("6. Add Subject to Teacher");
            System.out.println("7. Compare Students by Name");
            System.out.println("8. Compare Teachers by Age");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice)
            {
            case 1 -> addStudent();
            case 2 -> addTeacher();
            case 3 -> displayStudents();
            case 4 -> displayTeachers();
            case 5 -> addSubjectToStudent();
            case 6 -> addSubjectToTeacher();
            case 7 -> compareStudentsByName();
            case 8 -> compareTeachersByAge();
            case 9 -> System.out.println("Exiting...");
            default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        while (choice != 9);
    }

    // Method to add a new Student
    private static void addStudent()
    {
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter major: ");
        String major = scanner.nextLine();
        System.out.print("Enter credits (or leave blank for 0): ");
        String creditsInput = scanner.nextLine();
        int credits = creditsInput.isEmpty() ? 0 : Integer.parseInt(creditsInput);

        Student student = new Student(name, age, major, credits);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    // Method to add a new Teacher
    private static void addTeacher()
    {
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter major: ");
        String major = scanner.nextLine();
        System.out.print("Enter salary (or leave blank for 0.0): ");
        String salaryInput = scanner.nextLine();
        double salary = salaryInput.isEmpty() ? 0.0 : Double.parseDouble(salaryInput);

        Teacher teacher = new Teacher(name, age, major, salary);
        teachers.add(teacher);
        System.out.println("Teacher added successfully.");
    }

    // Method to display all Students
    private static void displayStudents()
    {
        if (students.isEmpty())
        {
            System.out.println("No students available.");
            return;
        }
        System.out.println("\n--- STUDENTS ---");
        for (Student student : students)
        {
            student.printDetails();
        }
    }

    // Method to display all Teachers
    private static void displayTeachers()
    {
        if (teachers.isEmpty())
        {
            System.out.println("No teachers available.");
            return;
        }
        System.out.println("\n--- TEACHERS ---");
        for (Teacher teacher : teachers)
        {
            teacher.printDetails();
        }
    }

    // Method to add a subject to a Student
    private static void addSubjectToStudent()
    {
        if (students.isEmpty())
        {
            System.out.println("No students available.");
            return;
        }
        displayStudents();
        System.out.print("Select Student index to add subject: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (index >= 0 && index < students.size())
        {
            System.out.print("Enter subject name: ");
            String subject = scanner.nextLine();
            boolean added = students.get(index).addSubject(subject);
            System.out.println(added ? "Subject added." : "Subject already exists.");
        }
        else
        {
            System.out.println("Invalid student index.");
        }
    }

    // Method to add a subject to a Teacher
    private static void addSubjectToTeacher()
    {
        if (teachers.isEmpty())
        {
            System.out.println("No teachers available.");
            return;
        }
        displayTeachers();
        System.out.print("Select Teacher index to add subject: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (index >= 0 && index < teachers.size())
        {
            System.out.print("Enter subject name: ");
            String subject = scanner.nextLine();
            boolean added = teachers.get(index).addSubject(subject);
            System.out.println(added ? "Subject added." : "Subject already exists.");
        }
        else
        {
            System.out.println("Invalid teacher index.");
        }
    }

    // Method to compare two Students by name
    private static void compareStudentsByName()
    {
        if (students.size() < 2)
        {
            System.out.println("At least two students are needed for comparison.");
            return;
        }
        displayStudents();
        System.out.print("Select first Student index: ");
        int index1 = scanner.nextInt();
        System.out.print("Select second Student index: ");
        int index2 = scanner.nextInt();
        if (index1 >= 0 && index1 < students.size() && index2 >= 0 && index2 < students.size() && index1 != index2)
        {
            boolean sameName = students.get(index1).sameName(students.get(index2));
            System.out.println(sameName ? "Students have the same name." : "Students have different names.");
        }
        else
        {
            System.out.println("Invalid indices selected.");
        }
    }

    // Method to compare two Teachers by age
    private static void compareTeachersByAge()
    {
        if (teachers.size() < 2)
        {
            System.out.println("At least two teachers are needed for comparison.");
            return;
        }
        displayTeachers();
        System.out.print("Select first Teacher index: ");
        int index1 = scanner.nextInt();
        System.out.print("Select second Teacher index: ");
        int index2 = scanner.nextInt();
        if (index1 >= 0 && index1 < teachers.size() && index2 >= 0 && index2 < teachers.size() && index1 != index2)
        {
            boolean sameAge = teachers.get(index1).sameAge(teachers.get(index2));
            System.out.println(sameAge ? "Teachers have the same age." : "Teachers have different ages.");
        }
        else
        {
            System.out.println("Invalid indices selected.");
        }
    }
}
