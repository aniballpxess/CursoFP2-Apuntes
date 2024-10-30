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
        this.age = Age.parseAge(age);
        this.major = major;
        subjects = new ArrayList<>();
    }

    public FullName getName()
    {
        return name;
    }

    public Age getAge()
    {
        return age;
    }

    public String getMajor()
    {
        return major;
    }

    // Remplazarlo tras implementacion completa de la clase Subject
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

    public void setName(FullName name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = Age.parseAge(age);
    }

    public void setMajor(String major)
    {
        this.major = major;
    }

    // Remplazarlo tras implementacion completa de la clase Subject
    public boolean addSubject(String subject)
    {
        if (hasSubject(subject))
        {
            return false;
        }
        subjects.add(subject);
        return true;
    }
    
    public boolean removeSubject(String subject)
    {
        return subjects.remove(subject);
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

    // Remplazarlo tras implementacion completa de la clase Subject
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

    public abstract void printDetails();
}