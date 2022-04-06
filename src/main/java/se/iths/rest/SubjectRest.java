package se.iths.rest;


import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    private SubjectService subjectService;

    @Path("")
    @GET
    public Response getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        if(subjects.isEmpty()) {
            return Response.ok("The table is empty", MediaType.TEXT_PLAIN_TYPE).build();
        }
        return Response.ok(subjects).build();
    }
}
