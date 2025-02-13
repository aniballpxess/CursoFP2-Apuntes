package edu.dam2.ad.ejercicios.objectdbapi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ObjectDB_Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myObjectDBUnit");
        EntityManager em = emf.createEntityManager();

        Person person1 = new Person("John Snow");
        Person person2 = new Person("Lara Croft");
        Person person3 = new Person("Leia Organa Solo");
        Person person4 = new Person("Padme Amidala Skywalker");

        em.getTransaction().begin();
        em.persist(person1);
        em.persist(person2);
        em.persist(person3);
        em.persist(person4);
        em.getTransaction().commit();

        // Query and print results
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        for (Person p : query.getResultList()) {
            System.out.println("Person: " + p.getName());
        }

        em.close();
        emf.close();
    }
}
