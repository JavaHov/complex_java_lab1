package se.iths.service;


import se.iths.customexceptions.EntityNotFoundException;
import se.iths.customexceptions.NotFoundException;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    public Teacher findTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public List<Teacher> findTeachersByFirstName(String name) {
        return entityManager.createQuery("select t from Teacher t where t.firstName like :teacherName", Teacher.class)
                .setParameter("teacherName", name)
                .getResultList();
    }

    public List<Teacher> findTeachersByLastName(String name) {
        return entityManager.createQuery("select t from Teacher t where t.lastName like :teacherName", Teacher.class)
                .setParameter("teacherName", name)
                .getResultList();
    }

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public void deleteTeacher(Long id) {
        try {
            Teacher teacher = entityManager.find(Teacher.class, id);
            for(Subject subject : teacher.getSubjects()) {
                teacher.removeSubject(subject);
            }
            entityManager.remove(teacher);

        } catch(Exception e) {
            throw new NotFoundException("Could not find teacher with id " + id);
        }
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        if(foundTeacher == null) {
            throw new NotFoundException("Could not find teacher with id " + id);
        }

        if(teacher.getFirstName() != null) {
            foundTeacher.setFirstName(teacher.getFirstName());
        }
        if(teacher.getLastName() != null) {
            foundTeacher.setLastName(teacher.getLastName());
        }
        return foundTeacher;
    }
}
