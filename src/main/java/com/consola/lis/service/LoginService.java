package com.consola.lis.service;

import com.consola.lis.dto.AuthResponse;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.dto.UserLoginResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;
import java.util.Collections;


@Service
@RequiredArgsConstructor
public class LoginService implements LoginServiceI {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public AuthResponse login(LoginRequestDTO loginRequestDTO)  {
        return null;

    }



    private void validarRol(UserLoginResponseDTO body) {
        if (body.getRole().equals("auxAdmin") || body.getRole().equals("auxProg")){
            body.setMessage("usuario valido");
        }
    }
}
