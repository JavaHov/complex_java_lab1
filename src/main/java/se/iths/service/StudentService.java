package se.iths.service;


import se.iths.customexceptions.EntityNotFoundException;
import se.iths.customexceptions.NotAuthorizedException;
import se.iths.customexceptions.NotFoundException;
import se.iths.customexceptions.StudentNotFoundException;
import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<Student> getStudentsByFirstName(String firstname) {
        return entityManager.createQuery("select s from Student s where s.firstName like :studentFirstName", Student.class)
                .setParameter("studentFirstName", firstname)
                .getResultList();
    }

    public void deleteStudent(Long id) {

        Student student = entityManager.find(Student.class, id);
        if(student == null) {
            throw new NotFoundException("Could not find student with id " + id);
        }
        entityManager.remove(student);
    }

    public Student update(Long id, Student student) throws EntityNotFoundException {

        Student foundStudent = entityManager.find(Student.class, id);
        if(foundStudent == null) {
            throw  new NotFoundException("Could not find student with id " + id);
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

    public Student replaceStudentInfo(Long id, Student student) {
            Student foundStudent = entityManager.find(Student.class, id);
            if(foundStudent == null) {
                throw new NotFoundException("Student not found");
            }
            return entityManager.merge(student);
    }

    public void createStudent(Student student) {
            entityManager.persist(student);
    }

    public Student addSubjectToStudent(Long studentId, Long subjectId) {

        Student student = entityManager.find(Student.class, studentId);
        if(student == null) {
            throw new NotFoundException("Could not find student with id " + studentId);
        }
        Subject subject = entityManager.find(Subject.class, subjectId);
        if(subject == null) {
            throw new NotFoundException("Could not find subject with id " + subjectId);
        }
        student.addSubject(subject);
        entityManager.merge(student);
        return entityManager.find(Student.class, student.getId());
    }
}
