package com.consola.lis.controller;

import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.dto.UserLoginResponseDTO;
import com.consola.lis.service.LoginService;
import com.consola.lis.service.LoginServiceI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/console-lis/api/login-request")
@RequiredArgsConstructor
public class LoginRequestController {

    private final LoginService loginService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception  {
        return new ResponseEntity<>(loginService.login(loginRequestDTO), HttpStatus.ACCEPTED);
    }

}
