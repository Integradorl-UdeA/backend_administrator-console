package com.consola.lis.controller;

import com.consola.lis.dto.AuthResponse;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/console-lis/auth/login")
@RequiredArgsConstructor
public class LoginRequestController {

    private final LoginService loginService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDTO loginRequest)   {
        return ResponseEntity.ok(loginService.login(loginRequest));

    }

}
