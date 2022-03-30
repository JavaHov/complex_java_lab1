package se.iths.service;


import se.iths.customexceptions.StudentNotFoundException;
import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Student> getAllStudents() {
        return entityManager.createQuery("select s from Student s", Student.class).getResultList();
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getStudentsByLastName(String lastname) {

        return entityManager.createQuery("select s from Student s where s.lastName like :studentLastName", Student.class)
                .setParameter("studentLastName", lastname)
                .getResultList();
    }

    public void deleteStudent(Long id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }

    public Student update(Long id, Student student) throws StudentNotFoundException {

        Student foundStudent = entityManager.find(Student.class, id);
        if(foundStudent == null) {
            throw  new StudentNotFoundException("Could not find student with id " + id);
        }

        if(student.getEmail() != null) {
            foundStudent.setEmail(student.getEmail());
        }
        if(student.getFirstName() != null) {
            foundStudent.setFirstName(student.getFirstName());
        }
        if(student.getLastName() != null) {
            foundStudent.setLastName(student.getLastName());
        }
        if(student.getPhoneNumber() != null) {
            foundStudent.setPhoneNumber(student.getPhoneNumber());
        }

        return foundStudent;
    }

    public Student replaceStudentInfo(Long id, Student student) throws StudentNotFoundException {
            Student foundStudent = entityManager.find(Student.class, id);
            if(foundStudent == null) {
                throw new StudentNotFoundException("Student not found");
            }
            return entityManager.merge(student);
    }

    public void createStudent(Student student) {
        entityManager.persist(student);
    }
}
