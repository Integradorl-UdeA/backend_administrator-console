package com.consola.lis.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admi/holi")
@RequiredArgsConstructor
public class Holi {


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public String hole() {
        return "holi";
    }
}
