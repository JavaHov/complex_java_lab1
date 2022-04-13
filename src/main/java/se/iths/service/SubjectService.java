package se.iths.service;


import se.iths.customexceptions.NotFoundException;
import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("select s from Subject s", Subject.class).getResultList();
    }

    public Subject findSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public Subject findSubjectByName(String name) {
        return entityManager.createQuery("select s from Subject s where s.title like :subjectName", Subject.class)
                .setParameter("subjectName", name)
                .getSingleResult();
    }

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public void deleteSubject(Long id) {
        Subject subject = entityManager.find(Subject.class, id);
        if(subject == null) {
            throw new NotFoundException("Could not find subject with id " + id);
        }
        subject.removeTeacher();
        for(Student student : subject.getStudents()) {
            subject.removeStudent(student);
        }
        entityManager.remove(subject);
    }

    public Subject updateSubject(Long id, Subject subject) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        if(foundSubject == null) {
            throw new NotFoundException("Could not find subject with id " + id);
        }

        if(subject.getTitle() != null) {
            foundSubject.setTitle(subject.getTitle());
        }
        return foundSubject;
    }

    public Subject addTeacherToSubject(Long subjectId, Long teacherId) {

        Subject subject = entityManager.find(Subject.class, subjectId);
        if(subject == null) {
            throw new NotFoundException("Could not find subject with id " + subjectId);
        }
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        if(teacher == null) {
            throw new NotFoundException("Could not find teacher with id " + teacherId);
        }
        subject.setTeacher(teacher);
        entityManager.persist(subject);
        return subject;
    }

    public Subject addStudentToSubject(Long subjectId, Long studentId) {

        Subject subject = entityManager.find(Subject.class, subjectId);
        if(subject == null) {
            throw new NotFoundException("Could not find subject with id " + subjectId);
        }
        Student student = entityManager.find(Student.class, studentId);
        if(student == null) {
            throw new NotFoundException("Could not find student with id " + studentId);
        }
        subject.addStudent(student);
        entityManager.merge(subject);
        return subject;
    }
}
