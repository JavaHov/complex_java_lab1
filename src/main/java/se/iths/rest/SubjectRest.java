package se.iths.rest;


import se.iths.customexceptions.ConflictException;
import se.iths.customexceptions.EntityNotFoundException;
import se.iths.customexceptions.NotFoundException;
import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @Path("{id}")
    @GET
    public Response findSubjectById(@PathParam("id") Long id) {
        Subject subject = subjectService.findSubjectById(id);
        if(subject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Could not find subject with id " + id)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build());
        }
        return Response.ok(subject).build();
    }

    @Path("subjectname")
    @GET
    public Response findSubjectByName(@QueryParam("name") String name) {
        Subject subject = subjectService.findSubjectByName(name);
        if(subject == null) {
            throw new NotFoundException("Could not find subject with name '" + name + "'.");
        }
        return Response.ok(subject).build();
    }

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        if(subject.getTitle() .isBlank()) {
            throw new ConflictException("title can not be blank.");
        }
        subjectService.createSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateSubject(@PathParam("id") Long id, Subject subject) {
        Subject updatedSubject = subjectService.updateSubject(id, subject);
        if(updatedSubject == null) {
            throw new ConflictException("Something went wrong trying to update subject.");
        }
        return Response.ok(updatedSubject, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        subjectService.deleteSubject(id);
        return Response.ok("Subject with id" + id + " was deleted.", MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

    @Path("{subjectId}/teacher/{teacherId}")
    @POST
    public Response addTeacherToSubject(@PathParam("subjectId") Long subjectId, @PathParam("teacherId") Long teacherId) {
        Subject subject = subjectService.addTeacherToSubject(subjectId, teacherId);
        return Response.ok(subject).build();
    }

}
