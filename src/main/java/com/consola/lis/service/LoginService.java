package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.model.entity.UserHelloLis;
import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.repository.UserHelloLisRepository;
import com.consola.lis.model.repository.UserLisRepository;
import com.consola.lis.util.exception.UserAuthenticationException;
import com.consola.lis.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LoginService  {

    private final AuthenticationManager authenticationManager;
    private final UserHelloLisRepository userHelloLisRepository;
    private final UserLisRepository userLisRepository;
    private final JwtService jwtService;


    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            System.out.println("olo");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            UserLis userLis = userLisRepository.findByUsername(loginRequest.getUsername()).orElseThrow(()-> new UserAuthenticationException("401", HttpStatus.UNAUTHORIZED, "Authentication failed, user not found"));
            UserHelloLis userHelloLis = userHelloLisRepository.findByUserLis(userLis).orElseThrow(()-> new UserAuthenticationException("401", HttpStatus.UNAUTHORIZED, "Authentication failed, user not found"));
            System.out.printf("opo"+userHelloLis);
            String token = jwtService.getToken(userLis);
            return AuthResponseDTO.builder()
                    .token(token)
                    .build();
        } catch (AuthenticationException ex) {
            System.out.printf("error "+ ex);
            throw new UserAuthenticationException("401", HttpStatus.UNAUTHORIZED, "mor acá no fue");
        }
    }


}
