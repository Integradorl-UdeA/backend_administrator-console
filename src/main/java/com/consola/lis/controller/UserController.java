package com.consola.lis.controller;

import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/console-lis/api/user")
public class UserController {

    @PostMapping
    public String welcome(){
        return "welcome from secure endpoint";
    }
}
