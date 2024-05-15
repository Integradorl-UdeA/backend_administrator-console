package com.consola.lis.controller;


import com.consola.lis.util.constans.ApiDescription;
import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.RegisterRequestDTO;
import com.consola.lis.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Resister for users", description = ApiDescription.DESCRIPTION_CONTROLLER_REGISTER)
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_REGISTER)
@RequiredArgsConstructor
public class RegisterRequestController {

    private final RegisterService registerService;

    @Operation(summary=ApiDescription.DESCRIPTION_REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void register(@RequestBody RegisterRequestDTO registerRequestDTO) {
            registerService.register(registerRequestDTO);



    }
}
