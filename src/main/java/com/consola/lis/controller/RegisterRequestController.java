package com.consola.lis.controller;


import com.consola.lis.dto.UserHelloLisDTO;
import com.consola.lis.dto.UserLisDTO;
import com.consola.lis.util.constans.ApiDescription;
import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Resister for users", description = ApiDescription.DESCRIPTION_CONTROLLER_REGISTER)
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_REGISTER)
@RequiredArgsConstructor
public class RegisterRequestController {

    private final RegisterService registerService;


    @Operation(summary=ApiDescription.DESCRIPTION_REGISTER_USER_LIS)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(EndpointConstant.ENDPOINT_REGISTER_USER_LIS)
    public void registerUserLis(@RequestBody UserLisDTO registerUserLisDTO) {
        registerService.registerUserLis(registerUserLisDTO);
    }

    @Operation(summary=ApiDescription.DESCRIPTION_REGISTER_USER_HELLO_LIS)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(EndpointConstant.ENDPOINT_REGISTER_USER_HELLO_LIS)
    public void registerUserHelloLis(@RequestBody UserHelloLisDTO registerUserHelloLisDTO) {
        registerService.registerUserHelloLis(registerUserHelloLisDTO);
    }


}
