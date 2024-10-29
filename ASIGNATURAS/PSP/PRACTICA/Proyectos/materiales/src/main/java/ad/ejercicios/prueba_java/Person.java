package ad.ejercicios.prueba_java;

import java.util.ArrayList;

abstract class Person
{
    private FullName name;
    private Age age;
    // Por implementar clases específicas
    private String major;
    private ArrayList<String> subjects;

    public Person(String name, int age, String major)
    {
        this.name = FullName.parseFullName(name.split(" "));
        this.age = new Age(age);
        this.major = major;
        subjects = new ArrayList<>();
    }

    public String getName()
    {
        return name.toString();
    }

    public int getAge()
    {
        return age.getAge();
    }

    public String getMajor()
    {
        return major;
    }

    public String getSubjects()
    {
        String subjectsStr = "";
        for (String subject : this.subjects) {
            subjectsStr += ", " + subject;
        }
        return subjectsStr;
    }

    public boolean sameName(Person p)
    {
        return this.name.equals(p.name);
    }

    public boolean sameAge(Person p)
    {
        return this.age.equals(p.age);
    }

    public boolean sameMajor(Person p)
    {
        return this.major.equals(p.major);
    }

    public boolean sameSubject(String hisSubject)
    {
        for (String mySubject : subjects)
        {
            if (mySubject.equals(hisSubject))
            {
                return true;
            }
        }
        return false;
    }
    
    public abstract void printDetails();
}