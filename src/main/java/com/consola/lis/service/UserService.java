package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.UserLdapDTO;
import com.consola.lis.dto.UserLisDTO;
import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.repository.UserLisRepository;
import com.consola.lis.util.exception.NotExistingException;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.enums.UserRole;
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

    public final UserLisRepository userLisRepository;
    private final RestTemplate restTemplate;
    public final PasswordEncoder passwordEncoder;
    public final JwtService jwtService;


    public UserLis getUser(String username) {
        return userLisRepository.findByUsername(username).orElseThrow(()-> new NotExistingException("404", HttpStatus.NOT_FOUND, "the category whit id " + username + " not exist "));
    }


    public AuthResponseDTO changeUserRole(String username, UserRole newRole) {

        UserLis user = userLisRepository.findByUsername(username).orElseThrow(()-> new NotExistingException("404", HttpStatus.NOT_FOUND, "the category whit id " + username + " not exist "));;
        user.setRole(newRole);
        userLisRepository.save(user);


        return AuthResponseDTO.builder()
                .token(jwtService.getToken(user))
                .build();
    }


    public UserLdapDTO getUserLDAP(String username) {

        ResponseEntity<UserLdapDTO> response = this.restTemplate.getForEntity("https://sistemas.udea.edu.co/api/ldap/login/{username}", UserLdapDTO.class, username);
        if (response.getBody() == null){
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the user whit " + username + "not exist");
        }
        return changeRole(Objects.requireNonNull(response.getBody()));

    }

    public UserLdapDTO changeRole(UserLdapDTO response) {

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
        if (!existUser(username)) {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the user whit " + username + "not exist");
        }
        userLisRepository.deleteByUsername(username);
    }


    public boolean existUser(String username) {
        return userLisRepository.existsByUsername(username);
    }
}