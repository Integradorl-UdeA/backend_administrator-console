package com.consola.lis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/console-lis/auth/prueba")
@RequiredArgsConstructor
public class PruebaController {
    @PostMapping
    public String hole(@RequestBody String hola)   {
        return hola;

    }
}
