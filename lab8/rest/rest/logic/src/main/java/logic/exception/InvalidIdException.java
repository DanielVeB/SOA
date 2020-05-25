package logic.exception;

import logic.config.exception.WebException;

import static logic.exception.codes.ErrorCode.INVALID_ID;

public class InvalidIdException extends WebException {

    public InvalidIdException(String id) {
        super(404, String.valueOf(INVALID_ID), "Id: " + id + "is invalid.");
    }
}
