package com.blog.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

@ControllerAdvice
@ResponseStatus
public class RestException {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<LinkedHashMap> noSuchElementException(NoSuchElementException exception, WebRequest request) {
        LinkedHashMap<String, String> error = new LinkedHashMap<String, String>(){{
            put("status_code", HttpStatus.NOT_FOUND.toString());
            put("error", exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<LinkedHashMap> alreadyExist(AlreadyExist exception, WebRequest request) {
        LinkedHashMap<String, String> error = new LinkedHashMap<String, String>(){{
            put("status_code", HttpStatus.CONFLICT.toString());
            put("error", exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(NotPublished.class)
    public ResponseEntity<LinkedHashMap> notPublished(NotPublished exception, WebRequest request) {
        LinkedHashMap<String, String> error = new LinkedHashMap<String, String>(){{
            put("status_code", HttpStatus.CONFLICT.toString());
            put("error", exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
