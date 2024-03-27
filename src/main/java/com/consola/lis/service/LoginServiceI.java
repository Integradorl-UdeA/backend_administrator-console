package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.LoginRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LoginServiceI {
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO) throws JsonProcessingException;
}
