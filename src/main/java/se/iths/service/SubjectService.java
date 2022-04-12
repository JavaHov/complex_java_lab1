package se.iths.service;


import se.iths.customexceptions.NotFoundException;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        Subject s = entityManager.find(Subject.class, id);
        entityManager.remove(s);
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
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        subject.setTeacher(teacher);
        entityManager.persist(subject);
        return subject;
    }
}
