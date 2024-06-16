package com.bloomtech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPathParameterException.class)
    public ResponseEntity<String> handleInvalidPathParameterException(InvalidPathParameterException ex) {
        return new ResponseEntity<>("Can not be found", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnsucessfullDatabaseOperationException.class)
    public ResponseEntity<String> handleUnsucessfullDatabaseOperationException(UnsucessfullDatabaseOperationException ex) {
        return new ResponseEntity<>("Can not be found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
