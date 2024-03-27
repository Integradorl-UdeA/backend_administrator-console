package com.consola.lis.controller;


import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.RegisterRequestDTO;
import com.consola.lis.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/console-lis/auth/register")
@RequiredArgsConstructor
public class RegisterRequestController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
            AuthResponseDTO response = registerService.register(registerRequestDTO);
            return ResponseEntity.ok(response);


    }
}
