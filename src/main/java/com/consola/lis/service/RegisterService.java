package com.consola.lis.service;

import com.consola.lis.dto.AuthResponse;
import com.consola.lis.dto.RegisterRequestDTO;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.repository.UserRepository;
import com.consola.lis.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse register(RegisterRequestDTO registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .role(UserRole.AUXADMI)
                .id(registerRequest.getId())
                .name(registerRequest.getName())
                .lastname(registerRequest.getLastname())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        System.out.println(registerRequest);
        System.out.println(user);

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
