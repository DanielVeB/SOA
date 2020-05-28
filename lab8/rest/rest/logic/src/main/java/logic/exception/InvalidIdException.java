package logic.exception;

import logic.config.exception.WebException;

import javax.ws.rs.core.Response;

import static logic.exception.codes.ErrorCode.INVALID_ID;

public class InvalidIdException extends WebException {

    public InvalidIdException(String id) {
        super(Response.Status.BAD_REQUEST.getStatusCode(), String.valueOf(INVALID_ID), "Id: '" + id + "' is invalid.");
    }
}
