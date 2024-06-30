package com.msgrande.application.controller.advice;

import com.msgrande.infraestructure.models.EmpleadoException;
import com.msgrande.domain.aggregates.constants.Constants;
import com.msgrande.domain.aggregates.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.IOException;
import java.util.Date;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleException(Exception e){
        ResponseError responseError = new ResponseError();
        responseError.setDate(new Date());
        responseError.setMessage(e.getMessage());
        responseError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.setError(Constants.ERROR_INTERNO_SERVIDOR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseError> handleNullPointerException(NullPointerException e) {
        ResponseError responseError = new ResponseError();
        responseError.setDate(new Date());
        responseError.setMessage(e.getMessage());
        responseError.setStatus(HttpStatus.CONFLICT.value());
        responseError.setError(Constants.CONFLICTO);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseError);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseError> handleIOException(IOException e) {
        ResponseError responseError = new ResponseError();
        responseError.setDate(new Date());
        responseError.setMessage(e.getMessage());
        responseError.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        responseError.setError(Constants.NO_ACEPTADO);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseError);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> handleRuntimeException(RuntimeException e) {
        ResponseError responseError = new ResponseError();
        responseError.setDate(new Date());
        responseError.setMessage(e.getMessage());
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());
        responseError.setError(Constants.ERROR_INTERNO_SERVIDOR);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(EmpleadoException.class)
    public ResponseEntity<ResponseError> handleEmpleadoException(EmpleadoException ex) {
        ResponseError responseError = new ResponseError();
        responseError.setDate(new Date());
        responseError.setMessage(ex.getMessage());
        responseError.setStatus(HttpStatus.CONFLICT.value());
        responseError.setError(Constants.CONFLICTO);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseError);
    }

}