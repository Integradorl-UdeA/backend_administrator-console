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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDTO> user(@RequestBody String userName){
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
