package com.consola.lis.service;

import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.dto.UserLoginResponseDTO;
import org.springframework.stereotype.Service;


@Service
public class LoginService implements LoginServiceI{

    @Override
    public UserLoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
