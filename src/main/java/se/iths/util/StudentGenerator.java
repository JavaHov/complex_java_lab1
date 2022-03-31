package se.iths.util;


import se.iths.entity.Student;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class StudentGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateStudents() {

        Student s1 = new Student("Mauro", "Scocco", "mauro@telia.com", "12345667");
        Student s2 = new Student("Lisa", "Ekdahl", "lisa@apple.com", "50698723");
        Student s3 = new Student("Joakim", "Thåström", "joakim@hemnet.com", "11090845");
        Student s4 = new Student("Jesper", "Parnevik", "jesper@comhem.com", "33405967");
        Student s5 = new Student("Musse", "Ekdahl", "m_ekdahl@tele2.com", "12345667");
        Student s6 = new Student("Peter", "Scocco", "peter@telia.com", "98765793");
        entityManager.persist(s1);
        entityManager.persist(s2);
        entityManager.persist(s3);
        entityManager.persist(s4);
        entityManager.persist(s5);
        entityManager.persist(s6);
    }
}
