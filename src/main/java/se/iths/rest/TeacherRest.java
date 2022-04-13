package se.iths.rest;


import se.iths.customexceptions.ConflictException;
import se.iths.customexceptions.EntityNotFoundException;
import se.iths.customexceptions.NotFoundException;
import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    private TeacherService teacherService;

    @Path("")
    @GET
    public Response getAllTeachers() {
        List<Teacher> teacherList = teacherService.getAllTeachers();
        if(teacherList.isEmpty()) {
            throw new NotFoundException("The table is empty.");
        }
        return Response.ok(teacherList).build();
    }

    @Path("{id}")
    @GET
    public Response getTeacherById(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if(foundTeacher == null) {
            throw new NotFoundException("Could not find teacher with id " + id);
        }
        return Response.ok(foundTeacher).build();
    }

    @Path("teacherfirst")
    @GET
    public Response findTeacherByFirstName(@QueryParam("firstname") String firstname) {
        List<Teacher> teacherList = teacherService.findTeachersByFirstName(firstname);
        if(teacherList.isEmpty()) {
            throw new NotFoundException("Could not find teacher with first name: " + firstname);
        }
        return Response.ok(teacherList).build();
    }

    @Path("teacherlast")
    @GET
    public Response findTeacherByLastName(@QueryParam("lastname") String lastname) {
        List<Teacher> teacherList = teacherService.findTeachersByLastName(lastname);
        if(teacherList.isEmpty()) {
            throw new NotFoundException("Found no teacher with last name: " + lastname);
        }
        return Response.ok(teacherList).build();
    }

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        if(checkEmptyFields(teacher)) {
            throw new ConflictException("Fields can not be empty.");
        }
        teacherService.createTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
        if(updatedTeacher == null) {
            throw new ConflictException("Could not update teacher for some reason...");
        }
        return Response.ok(updatedTeacher, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        try {
            teacherService.deleteTeacher(id);
            return Response.ok("Teacher with id " + id + " deleted.").build();
        } catch(Exception e) {
            throw new NotFoundException("Could not delete. Teacher with id " + id + " was not found");
        }
    }




    private boolean checkEmptyFields(Teacher teacher) {
        return teacher.getFirstName().isBlank() || teacher.getLastName().isBlank();
    }
}
