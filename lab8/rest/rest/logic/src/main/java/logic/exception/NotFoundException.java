package logic.exception;


import logic.config.exception.WebException;
import logic.exception.codes.ErrorCode;

import javax.ws.rs.core.Response;

public class NotFoundException extends WebException {

    public NotFoundException(String userId) {
        super(Response.Status.NOT_FOUND.getStatusCode(), String.valueOf(ErrorCode.NOT_FOUND), "Entity with id " + userId + " doesn't exist");
    }
}
