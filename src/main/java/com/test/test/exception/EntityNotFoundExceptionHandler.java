package com.test.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityNotFoundExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityException> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        EntityException entityException = new EntityException(entityNotFoundException.getMessage(), entityNotFoundException.getCause(),
                HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entityException, HttpStatus.NOT_FOUND);
    }
}
