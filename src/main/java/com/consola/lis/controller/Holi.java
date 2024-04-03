package com.consola.lis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/holi")
@RequiredArgsConstructor
public class Holi {

    @GetMapping
    public String hole() {
        return "holi";
    }
}
