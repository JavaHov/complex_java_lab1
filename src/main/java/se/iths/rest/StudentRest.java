package se.iths.rest;



import se.iths.customexceptions.NoValidFieldsException;
import se.iths.customexceptions.NotFoundException;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @GET
    public Response getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if(students.isEmpty()) {
            throw new NotFoundException("No students i table.");
        }
        return Response.ok(students).build();
    }

    @Path("{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        if(student == null) {
            throw new NotFoundException("No student with id " + id + " in database.");
        }
        return Response.ok(student).build();
    }

    @Path("querylast")
    @GET
    public Response getStudentsByLastName(@QueryParam("lastname") String lastname) {
        List<Student> students = studentService.getStudentsByLastName(lastname);
        if (students.isEmpty()) {
            throw new NotFoundException("No students with first name: " + lastname);
        }
        return Response.ok(students).build();
    }

    @Path("queryfirst")
    @GET
    public Response getStudentByFirstName(@QueryParam("firstname") String firstname) {
        List<Student> students = studentService.getStudentsByFirstName(firstname);
        if (students.isEmpty()) {
            throw new NotFoundException("No students with first name: " + firstname);
        }
        return Response.ok(students).build();
    }


    @Path("")
    @POST
    public Response createStudent(Student student) {
        if(checkEmptyFields(student)) {
            throw new NoValidFieldsException("Some fields are empty");
        }
        studentService.createStudent(student);
        return Response.ok(student).build();
    }


    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return Response.ok("Student with id " + id + " deleted.", MediaType.TEXT_PLAIN_TYPE).build();
        } catch(Exception e) {
            throw new NotFoundException("Could not find student with id " + id);
        }
    }

    @Path("{id}")
    @PATCH
    public Response update(@PathParam("id") Long id, Student student) {
        Student updatedStudent = studentService.update(id, student);
        if(updatedStudent == null) {
            throw new NotFoundException("Student not found");
        }
        return Response.ok(updatedStudent).build();
    }

    @Path("{id}")
    @PUT
    public Response replaceStudentInfo(@PathParam("id") Long id, Student student) {
            Student replacedStudent = studentService.replaceStudentInfo(id, student);
            if(replacedStudent == null) {
                throw new NotFoundException("Could not update student with id " + id);
            }
            return Response.ok(replacedStudent).build();
    }

    @Path("{studentId}/subject/{subjectId}")
    @PATCH
    public Response addSubjectToStudent(@PathParam("studentId")Long studentId, @PathParam("subjectId")Long subjectId) {
        Student student = studentService.addSubjectToStudent(studentId, subjectId);
        return Response.ok(student).build();
    }

    private static boolean checkEmptyFields(Student student) {
        return student.getFirstName().isBlank() || student.getLastName().isBlank() || student.getEmail().isBlank();
    }




}
