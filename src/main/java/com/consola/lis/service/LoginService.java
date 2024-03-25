package com.consola.lis.service;

import com.consola.lis.dto.AuthResponse;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService implements LoginServiceI {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequestDTO loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("loginrequest"+ loginRequest);
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        System.out.println(user);
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }


}
