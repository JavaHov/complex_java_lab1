package se.iths.customexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EntityNotFoundException extends WebApplicationException {



    public EntityNotFoundException(String message) {

        super(Response.status(Response.Status.NOT_FOUND)
                .entity(message).type("text/plain").build());
    }
}
