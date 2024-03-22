package com.consola.lis.service;

import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.dto.UserLoginResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;


@Service
@RequiredArgsConstructor
public class LoginService implements LoginServiceI {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserLoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws JsonProcessingException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String bodyJson = objectMapper.writeValueAsString(loginRequestDTO);
        HttpEntity<String> httpEntity = new HttpEntity<>(bodyJson, headers);
        ResponseEntity<UserLoginResponseDTO> response = this.restTemplate.postForEntity("https://sistemas.udea.edu.co/api/ldap/login", httpEntity, UserLoginResponseDTO.class);
        return response.getBody();
    }

    private boolean validarRol(UserLoginResponseDTO body) {
        return (body.getRole().equals("auxlis") || body.getRole().equals("auxprog"));
    }
}
