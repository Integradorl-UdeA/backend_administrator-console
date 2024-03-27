package com.consola.lis.service;

import com.consola.lis.dto.AuthResponse;
import com.consola.lis.dto.RegisterRequestDTO;
import com.consola.lis.exception.UserAlreadyExistsException;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.repository.UserRepository;
import com.consola.lis.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequestDTO registerRequest) {

        boolean existingOneData = userRepository.existsUserNameByIdOrUsername(registerRequest.getUsername(), registerRequest.getId());
        boolean existingUser = userRepository.existsUserNameByIdAndUsername(registerRequest.getUsername(), registerRequest.getId());
        if (existingUser) {
            throw new UserAlreadyExistsException("409", HttpStatus.CONFLICT, "User already exists");
        }else if(existingOneData){
            throw new UserAlreadyExistsException("409", HttpStatus.CONFLICT, "User already exists");
        }else {
            User user = User.builder()
                    .username(registerRequest.getUsername())
                    .role(UserRole.AUXADMI)
                    .id(registerRequest.getId())
                    .name(registerRequest.getName())
                    .lastname(registerRequest.getLastname())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .build();

            userRepository.save(user);


            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        }
    }
}




