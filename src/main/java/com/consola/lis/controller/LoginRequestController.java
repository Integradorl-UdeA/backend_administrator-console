package com.consola.lis.controller;

import com.consola.lis.constans.EndpointConstant;
import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Login for users", description = "something")
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_LOGIN)
@RequiredArgsConstructor
public class LoginRequestController {

    private final LoginService loginService;

    @Operation(summary = "This method is used to login.")
    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequest)   {
        return ResponseEntity.ok(loginService.login(loginRequest));

    }

}
