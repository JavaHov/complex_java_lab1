package se.iths.rest;


import se.iths.customexceptions.EntityNotFoundException;
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
            return Response.ok("The table is empty.", MediaType.TEXT_PLAIN_TYPE).build();
        }
        return Response.ok(teacherList).build();
    }

    @Path("{id}")
    @GET
    public Response getTeacherById(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.findTeacherById(id);
        if(foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No teacher with id " + id).type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundTeacher).build();
    }

    @Path("teacherfirst")
    @GET
    public Response findTeacherByFirstName(@QueryParam("firstname") String firstname) {
        List<Teacher> teacherList = teacherService.findTeachersByFirstName(firstname);
        if(teacherList.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity("No teacher with firstname " + firstname + " found.")
                    .build());
        }
        return Response.ok(teacherList).build();
    }

    @Path("teacherlast")
    @GET
    public Response findTeacherByLastName(@QueryParam("lastname") String lastname) {
        List<Teacher> teacherList = teacherService.findTeachersByLastName(lastname);
        if(teacherList.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity("No teacher with lastname " + lastname + " found.")
                    .build());
        }
        return Response.ok(teacherList).build();
    }

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        if(checkEmptyFields(teacher)) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                    .entity("Firstname or lastname can not be empty.")
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build());
        }
        teacherService.createTeacher(teacher);
        return Response.ok(teacher, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
        if(updatedTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Could not find teacher with id " + id)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build());
        }
        return Response.ok(updatedTeacher, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        try {
            teacherService.deleteTeacher(id);
            return Response.ok("Teacher with id " + id + " deleted.", MediaType.APPLICATION_JSON_TYPE).build();
        } catch(Exception e) {
            throw new EntityNotFoundException("Could not delete. Teacher with id " + id + " was not found");
        }
    }


    private boolean checkEmptyFields(Teacher teacher) {
        return teacher.getFirstName().isBlank() || teacher.getLastName().isBlank();
    }
}
