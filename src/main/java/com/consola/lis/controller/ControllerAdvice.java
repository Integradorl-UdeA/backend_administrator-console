package com.consola.lis.controller;

import com.consola.lis.dto.ErrorDTO;
import com.consola.lis.util.exception.*;
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

    @ExceptionHandler(value = IllegalParameterInRequest.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(IllegalParameterInRequest ex){
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

    @ExceptionHandler(value = JwtExpiredException.class)
    public ResponseEntity<ErrorDTO> handleJwtExpiredException(JwtExpiredException ex){
        ErrorDTO error = ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }
}
