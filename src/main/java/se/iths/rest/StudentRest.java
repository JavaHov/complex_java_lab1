package se.iths.rest;


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
        return Response.ok(students).build();
    }

    @Path("{id}")
    @GET
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        return Response.ok(student).build();
    }

    @Path("query")
    @GET
    public Response getStudentsByLastName(@QueryParam("lastname") String lastname) {
        List<Student> students = studentService.getStudentsByLastName(lastname);
        return Response.ok(students).build();
    }

    @Path("")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.ok(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.ok("Student with id " + id + " was deleted.").build(); //Vet inte om det funkar...
    }

    @Path("{id}")
    @PATCH
    public Response update(@PathParam("id") Long id, Student student) {
        Student updatedStudent = studentService.update(id, student);
        return Response.ok(updatedStudent).build();
    }

    @Path("")
    @PUT
    public Response replaceStudentInfo(Student student) {
        Student replacedStudent = studentService.replaceStudentInfo(student);
        return Response.ok(replacedStudent).build();
    }

}
