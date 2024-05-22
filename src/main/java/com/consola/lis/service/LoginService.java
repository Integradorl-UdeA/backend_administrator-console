package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.repository.UserLisRepository;
import com.consola.lis.util.exception.UserAuthenticationException;
import com.consola.lis.jwt.JwtService;
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
    private final UserLisRepository userLisRepository;
    private final JwtService jwtService;


    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            UserLis userLis = userLisRepository.findByUsername(loginRequest.getUsername()).orElseThrow(()-> new UserAuthenticationException("401", HttpStatus.UNAUTHORIZED, "Authentication failed, user not found"));
            String token = jwtService.getToken(userLis);
            return AuthResponseDTO.builder()
                    .token(token)
                    .build();
        } catch (AuthenticationException ex) {
            throw new UserAuthenticationException("401", HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
    }


}
