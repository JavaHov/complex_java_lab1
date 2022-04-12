package se.iths.customexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {
    public NotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new BasicResponse("404", message))
                .type(MediaType.APPLICATION_JSON).build());
    }
}
