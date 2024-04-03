package com.consola.lis.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/holi")
@RequiredArgsConstructor
public class holi {

    @GetMapping
    public String holi() {
        return "holi";
    }
}
