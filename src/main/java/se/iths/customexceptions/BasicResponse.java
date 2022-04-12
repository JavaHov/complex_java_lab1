package se.iths.customexceptions;

public class BasicResponse {

    public String httpStatus;

    public String message;

    public BasicResponse() {}

    public BasicResponse(String internalStatus, String message){
        this.httpStatus = internalStatus;
        this.message = message;
    }
}
