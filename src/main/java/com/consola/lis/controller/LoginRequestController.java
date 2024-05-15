package com.consola.lis.controller;

import com.consola.lis.util.constans.ApiDescription;
import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Login for users", description = ApiDescription.DESCRIPTION_CONTROLLER_LOGIN)
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_LOGIN)
@RequiredArgsConstructor
public class LoginRequestController {

    private final LoginService loginService;



    @Operation(summary = ApiDescription.DESCRIPTION_LOGIN)
    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequest)   {
        return ResponseEntity.ok(loginService.login(loginRequest));

    }

}
