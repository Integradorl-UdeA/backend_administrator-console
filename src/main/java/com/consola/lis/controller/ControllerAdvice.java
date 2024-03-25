package com.consola.lis.controller;

import com.consola.lis.dto.ErrorDTO;
import com.consola.lis.exception.UserAlreadyExistsException;
import com.consola.lis.exception.UserAuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(UserAlreadyExistsException ex){
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

}
