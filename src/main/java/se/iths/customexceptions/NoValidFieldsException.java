package se.iths.customexceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NoValidFieldsException extends WebApplicationException {
    public NoValidFieldsException(String message) {
        super(Response.status(Response.Status.CONFLICT)
                .entity(new BasicResponse("409", message))
                .type(MediaType.APPLICATION_JSON).build());
    }
}
