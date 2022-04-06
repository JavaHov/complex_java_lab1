package se.iths.rest;


import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
