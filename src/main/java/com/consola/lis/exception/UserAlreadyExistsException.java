package com.consola.lis.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
    private final String code;
    private final HttpStatus status;

    public UserAlreadyExistsException(String code, HttpStatus status, String message){
        super(message);
        this.status = status;
        this.code = code;
    }
}
