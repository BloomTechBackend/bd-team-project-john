package com.bloomtech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnsucessfullDatabaseOperationException extends RuntimeException {
    public UnsucessfullDatabaseOperationException(String message) {
        super(message);
    }
}
