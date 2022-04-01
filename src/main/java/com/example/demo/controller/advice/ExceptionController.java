package com.example.demo.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler {

    public static final String CONSTRAINT_VIOLATION_EXCEPTION_ENCOUNTERED = "Constraint violation exception encountered: {} {}";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolation(ConstraintViolationException ex) {
        String msg = ex.getConstraintViolations().stream()
                           .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
                           .collect(Collectors.joining(", "));
        log.debug(CONSTRAINT_VIOLATION_EXCEPTION_ENCOUNTERED, msg, ex);
        return getResponseEntity(msg, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity getResponseEntity(String msg, HttpStatus httpStatus) {
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("error", msg);
        response.put("status", String.valueOf(httpStatus.value()));
        return new ResponseEntity(response, httpStatus);
    }

}