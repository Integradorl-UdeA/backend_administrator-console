package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.util.exception.UserAuthenticationException;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService  {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;


    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(()-> new UserAuthenticationException("401", HttpStatus.UNAUTHORIZED, "Authentication failed, user not found"));
            String token = jwtService.getToken(user);
            return AuthResponseDTO.builder()
                    .token(token)
                    .build();
        } catch (AuthenticationException ex) {
            throw new UserAuthenticationException("401", HttpStatus.UNAUTHORIZED, "Authentication failed, user not found");
        }
    }


}
