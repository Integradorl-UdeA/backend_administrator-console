package com.consola.lis.service;

import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.dto.UserLoginResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LoginServiceI {
    UserLoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws JsonProcessingException;
}
