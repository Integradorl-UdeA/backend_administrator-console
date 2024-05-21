package com.consola.lis.service;

import com.consola.lis.dto.*;
import com.consola.lis.model.entity.UserHelloLis;
import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.repository.UserHelloLisRepository;
import com.consola.lis.model.repository.UserLisRepository;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserLisRepository userLisRepository;
    private final UserHelloLisRepository userHelloLisRepository;
    private final PasswordEncoder passwordEncoder;


    public UserLis registerUserLis(UserLisDTO registerUserLisDTO) {
        if(userLisRepository.existsById(registerUserLisDTO.getIdUser()) ||  userLisRepository.existsByUsername(registerUserLisDTO.getUsername())){
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "User already exists");
        }
        UserLis userLis = UserLis.builder()
                .username(registerUserLisDTO.getUsername())
                .role(registerUserLisDTO.getRole())
                .idUser(registerUserLisDTO.getIdUser())
                .name(registerUserLisDTO.getName())
                .lastname(registerUserLisDTO.getLastname())
                .build();
        return userLisRepository.save(userLis);
    }

    public void registerUserHelloLis(UserHelloLisDTO registerUserHelloLisDTO) {
        if(userLisRepository.existsById(registerUserHelloLisDTO.getIdUser())){
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "User already exists");
        }
        UserLisDTO registerUserLisDTO = UserLisDTO.builder()
                .username(registerUserHelloLisDTO.getUsername())
                .role(registerUserHelloLisDTO.getRole())
                .idUser(registerUserHelloLisDTO.getIdUser())
                .name(registerUserHelloLisDTO.getName())
                .lastname(registerUserHelloLisDTO.getLastname())
                .build();

        UserHelloLis userHelloLis = UserHelloLis.builder()
                .password(passwordEncoder.encode(registerUserHelloLisDTO.getPassword()))
                .userLis(registerUserLis(registerUserLisDTO))
                .build();

        userHelloLisRepository.save(userHelloLis);
    }


}




