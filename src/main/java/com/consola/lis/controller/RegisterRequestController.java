package com.consola.lis.controller;


import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.RegisterRequestDTO;
import com.consola.lis.service.RegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Resister for users", description = "something")
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_REGISTER)
@RequiredArgsConstructor
public class RegisterRequestController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
            AuthResponseDTO response = registerService.register(registerRequestDTO);
            return ResponseEntity.ok(response);


    }
}
