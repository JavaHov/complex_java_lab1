package se.iths.util;


import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class EntityGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateEntities() {

        Student s1 = new Student("Mauro", "Scocco", "mauro@telia.com", "12345667");
        Student s2 = new Student("Lisa", "Ekdahl", "lisa@apple.com", "50698723");
        Student s3 = new Student("Joakim", "Thåström", "joakim@hemnet.com", "11090845");
        Student s4 = new Student("Jesper", "Parnevik", "jesper@comhem.com", "33405967");
        Student s5 = new Student("Musse", "Ekdahl", "m_ekdahl@tele2.com", "12345667");
        Student s6 = new Student("Peter", "Scocco", "peter@telia.com", "98765793");
        Student s7 = new Student("Lisa", "Johansson", "lisa_j@telia.com", "11098457");
        Student s8 = new Student("Asta", "Piedmount", "asta_p@telia.com", "32045685");
        Student s9 = new Student("Karin", "Vittstam", "karinv@telia.com", "12340985");
        entityManager.persist(s1);
        entityManager.persist(s2);
        entityManager.persist(s3);
        entityManager.persist(s4);
        entityManager.persist(s5);
        entityManager.persist(s6);
        entityManager.persist(s7);
        entityManager.persist(s8);
        entityManager.persist(s9);

        Teacher t1 = new Teacher("Johan", "Tengbom");
        Teacher t2 = new Teacher("Anna", "Larsson");
        Teacher t3 = new Teacher("Beatrice", "Adersson");
        entityManager.persist(t1);
        entityManager.persist(t2);
        entityManager.persist(t3);

        Subject sub1 = new Subject("Matematik");
        Subject sub2 = new Subject("Engelska");
        Subject sub3 = new Subject("Svenska");
        Subject sub4 = new Subject("Geografi");
        Subject sub5 = new Subject("Musik");
        entityManager.persist(sub1);
        entityManager.persist(sub2);
        entityManager.persist(sub3);
        entityManager.persist(sub4);
        entityManager.persist(sub5);

    }
}
