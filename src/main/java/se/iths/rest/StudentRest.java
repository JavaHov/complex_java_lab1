package se.iths.rest;


import se.iths.customexceptions.EntityNotFoundException;
import se.iths.customexceptions.StudentNotFoundException;
import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;


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
            return Response.ok("The table is empty.", MediaType.TEXT_PLAIN_TYPE).build();
        }
        return Response.ok(students).build();
    }

    @Path("{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) throws EntityNotFoundException {
        Student student = studentService.findStudentById(id);
        if(student == null) {
            throw new EntityNotFoundException("No student with id " + id + " in database.");
        }
        return Response.ok(student).build();
    }

    @Path("querylast")
    @GET
    public Response getStudentsByLastName(@QueryParam("lastname") String lastname) {
        List<Student> students = studentService.getStudentsByLastName(lastname);
        if(students.isEmpty()) {
            throw new WebApplicationException(Response.status(NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity("No students in table with lastname: " + lastname)
                    .build());
        }
        return Response.ok(students).build();
    }

    @Path("queryfirst")
    @GET
    public Response getStudentByFirstName(@QueryParam("firstname") String firstname) {
        List<Student> students = studentService.getStudentsByFirstName(firstname);
        if(students.isEmpty()) {
            throw new WebApplicationException(Response.status(NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity("No student in table with firstname: " + firstname)
                    .build());
        }
        return Response.ok(students).build();
    }

    @Path("")
    @POST
    public Response createStudent(Student student) throws Exception {
        try {
            studentService.createStudent(student);
            return Response.ok(student).build();
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return Response.ok("Student with id " + id + " deleted.", MediaType.TEXT_PLAIN_TYPE).build();
        } catch(Exception e) {
            throw new EntityNotFoundException("Could not delete. Student with id " + id + " was not found");
        }
    }

    @Path("{id}")
    @PATCH
    public Response update(@PathParam("id") Long id, Student student) throws EntityNotFoundException {
        Student updatedStudent = studentService.update(id, student);
        if(updatedStudent == null) {
            throw new EntityNotFoundException("Student not found");
        }
        return Response.ok(updatedStudent).build();
    }

    @Path("{id}")
    @PUT
    public Response replaceStudentInfo(@PathParam("id") Long id, Student student) throws StudentNotFoundException {
            Student replacedStudent = studentService.replaceStudentInfo(id, student);
            if(replacedStudent == null) {
                throw new StudentNotFoundException("Could not update student with id " + id);
            }
            return Response.ok(replacedStudent).build();
    }

}
