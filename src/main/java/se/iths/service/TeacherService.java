package se.iths.service;


import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
        Teacher t = entityManager.find(Teacher.class, id);
        entityManager.remove(t);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);

        if(teacher.getFirstName() != null) {
            foundTeacher.setFirstName(teacher.getFirstName());
        }
        if(teacher.getLastName() != null) {
            foundTeacher.setLastName(teacher.getLastName());
        }
        return foundTeacher;
    }
}
