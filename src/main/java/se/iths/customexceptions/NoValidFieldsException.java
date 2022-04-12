package se.iths.customexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NoValidFieldsException extends WebApplicationException {
    public NoValidFieldsException(String message) {
        super(Response.status(Response.Status.PARTIAL_CONTENT)
                .entity(new BasicResponse("40something", message))
                .type(MediaType.APPLICATION_JSON).build());
    }
}
