package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.UserDTO;
import com.consola.lis.util.mapper.UserMapper;
import com.consola.lis.util.exception.NotExistingException;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.enums.UserRole;
import com.consola.lis.model.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    private final RestTemplate restTemplate;
    public final PasswordEncoder passwordEncoder;
    public final JwtService jwtService;


    public UserDTO getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, "the category whit id " + username + " not exist ");
        } else return UserMapper.mapToUserDTO(user.get());
    }


    public AuthResponseDTO changeUserRole(String username, UserRole newRole) {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the user whit  " + username + " not exist");
        }

        user.get().setRole(newRole);
        userRepository.save(user.get());


        return AuthResponseDTO.builder()
                .token(jwtService.getToken(user.get()))
                .build();
    }


    public UserDTO getUserLDAP(String username) {

        ResponseEntity<UserDTO> response = this.restTemplate.getForEntity("https://sistemas.udea.edu.co/api/ldap/login/{username}", UserDTO.class, username);
        if (response.getBody() == null){
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the user whit " + username + "not exist");
        }
        return changeRole(Objects.requireNonNull(response.getBody()));

    }

    public UserDTO changeRole(UserDTO response) {

        switch (response.getRole()) {
            case "1005" -> response.setRole(String.valueOf(UserRole.STUDENT));
            case "503" -> response.setRole(String.valueOf(UserRole.PROFESOR));
            case "502" -> response.setRole(String.valueOf(UserRole.AUXPROG));
            case "1006" -> response.setRole(String.valueOf(UserRole.AUXADMI));
            default -> throw new IllegalStateException("Unexpected value: " + response.getRole());
        }
        return response;
    }

    @Transactional
    public void deleteUser(String username) {
        if (existUser(username)) {
            userRepository.deleteByUsername(username);
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the user whit " + username + "not exist");
        }
    }


    public boolean existUser(String username) {
        return userRepository.existsByUsername(username);
    }
}