package com.api.employeesdata.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.employeesdata.entities.APIControllerErrorResponse;
import com.api.employeesdata.exceptions.InvalidEmployeeException;
import com.api.employeesdata.exceptions.InvalidUserException;

@ControllerAdvice
public class EmployeeApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<APIControllerErrorResponse> handleException(InvalidEmployeeException e) {
        
        APIControllerErrorResponse error = new APIControllerErrorResponse();

        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<APIControllerErrorResponse> handleException(InvalidUserException e) {
        
        APIControllerErrorResponse error = new APIControllerErrorResponse();

        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<APIControllerErrorResponse> handleException(Exception e) {
        
        APIControllerErrorResponse error = new APIControllerErrorResponse();

        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
