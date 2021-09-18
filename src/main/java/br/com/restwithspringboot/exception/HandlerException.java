package br.com.restwithspringboot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<ResponseError> ex(HttpServletRequest req) {
        return ResponseEntity.status(BAD_REQUEST).body(new ResponseError("INVALID REQUEST", "4000", req.getRequestURI()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<ResponseError> exs(RuntimeException e,HttpServletRequest req) {
        return ResponseEntity.status(NOT_FOUND).body(new ResponseError(e.getMessage(), "4001", req.getRequestURI()));
    }
}