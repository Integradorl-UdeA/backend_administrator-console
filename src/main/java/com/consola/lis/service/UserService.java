package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
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
        System.out.println("holi"+newRole.name());
        UserLis user = userLisRepository.findByUsername(username).orElseThrow(()-> new NotExistingException("404", HttpStatus.NOT_FOUND, "the category whit id " + username + " not exist "));

        user.setRole(newRole);
        userLisRepository.save(user);


        return AuthResponseDTO.builder()
                .token(jwtService.getToken(user))
                .build();
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

    public Boolean checkExistUser(String username) {
        try {
            if (existUser(username)) {
                return true;
            }
            ResponseEntity<UserLisDTO> response = this.restTemplate.getForEntity("https://sistemas.udea.edu.co/api/ldap/login/{username}", UserLisDTO.class, username);
            UserLisDTO userLdap = response.getBody();
            if (userLdap == null) {
                return false;
            } else {
                UserLis userLis = UserLis.builder()
                        .idUser(userLdap.getIdUser())
                        .username(userLdap.getUsername())
                        .name(userLdap.getName())
                        .lastname(userLdap.getLastname())
                        .role(userLdap.getRole())
                        .build();

                userLisRepository.save(userLis);
                return true;
            }
        }catch (Exception ex){
            return false;
        }
    }
}