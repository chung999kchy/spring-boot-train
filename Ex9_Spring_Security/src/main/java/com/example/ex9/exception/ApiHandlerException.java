package com.example.ex9.exception;

import com.example.ex9.model.responseapi.MsgError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class ApiHandlerException extends Exception{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<MsgError> bindExceptionHandler(BindException ex){
        String error = "Incorrect validate";
        List<String> messages = new ArrayList<>();
        for(ObjectError objectError : ex.getAllErrors()) {
            messages.add(objectError.getDefaultMessage());
        }
        int code = HttpStatus.BAD_REQUEST.value();
        return new ResponseEntity<>(new MsgError(error, messages, code), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MsgError> resourceNotFoundException(EntityNotFoundException ex) {
        String error = "Not found entity";
        List<String> messages = new ArrayList<>();
        messages.add(ex.getLocalizedMessage());
        int code = HttpStatus.NOT_FOUND.value();
        return new ResponseEntity<>(new MsgError(error, messages, code), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MsgError> globalExceptionHandler(Exception ex) {
        String error = "Exception";
        List<String> messages = new ArrayList<>();
        messages.add(ex.getLocalizedMessage());
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return new ResponseEntity<>(new MsgError(error, messages, code), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
