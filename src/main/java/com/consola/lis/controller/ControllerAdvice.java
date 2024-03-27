package com.consola.lis.controller;

import com.consola.lis.dto.ErrorDTO;
import com.consola.lis.exception.AlreadyExistsException;
import com.consola.lis.exception.NotExistingException;
import com.consola.lis.exception.UserAuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(AlreadyExistsException ex){
        ErrorDTO error = ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(value = UserAuthenticationException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(UserAuthenticationException ex){
        ErrorDTO error = ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }


    @ExceptionHandler(value = NotExistingException.class)
    public ResponseEntity<ErrorDTO> handleNotExistingException(NotExistingException ex){
        ErrorDTO error = ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

}
