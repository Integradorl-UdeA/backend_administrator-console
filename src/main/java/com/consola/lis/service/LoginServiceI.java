package com.consola.lis.service;

import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.dto.UserLoginResponseDTO;

public interface LoginServiceI {
    UserLoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
