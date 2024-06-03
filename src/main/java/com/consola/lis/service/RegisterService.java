package com.consola.lis.service;

import com.consola.lis.dto.*;
import com.consola.lis.model.entity.UserHelloLis;
import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.enums.UserRole;
import com.consola.lis.model.repository.UserHelloLisRepository;
import com.consola.lis.model.repository.UserLisRepository;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.NotExistingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserLisRepository userLisRepository;
    private final UserHelloLisRepository userHelloLisRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;


    @Value("${oAuthTokenAsoneMicroservice}")
    private String oAuthTokenAsone;

    @Value("${asoneMicroservice}")
    private String asoneMicroservice;

    @Value("${asoneTypeConnection}")
    private String asoneTypeConnection;

    @Value("${asoneMicroservice}")
    private String endpointAsone;




    public UserLis registerUserLis(UserLisDTO registerUserLisDTO) {
        boolean userExists = userLisRepository.existsById(registerUserLisDTO.getIdUser())
                || userLisRepository.existsByUsername(registerUserLisDTO.getUsername());
        if (userExists) {
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "User already exists");
        }

        if (registerUserLisDTO.getRole() == UserRole.STUDENT && !getBasicAcademicInfo("ACTIVO",registerUserLisDTO.getIdUser())) {
            throw new NotExistingException("403", HttpStatus.FORBIDDEN, "The student does not belong to systems engineering");
        }

        if (registerUserLisDTO.getRole() == UserRole.GRADUATED && !getBasicAcademicInfo("GRADUADO",registerUserLisDTO.getIdUser())) {
            throw new NotExistingException("403", HttpStatus.FORBIDDEN, "The student does not belong to systems engineering");
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

    private boolean getBasicAcademicInfo(String state,String identificationNumber) {
        try{
            String url = endpointAsone + identificationNumber;

            HttpHeaders headers = new HttpHeaders();
            headers.set("OAuth_Token", oAuthTokenAsone);
            headers.set("Tipo_Conexion", asoneTypeConnection);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode objects = root.path("object");

            if (objects.isArray() && objects.size() > 0) {
                JsonNode lastObject = objects.get(objects.size() - 1);
                int program = lastObject.path("programa").asInt();
                String stateProgram = lastObject.path("estadoAlumnoPrograma").asText();
                return program == 504 && state.equals(stateProgram);
            }

            return false;

        }catch(Exception e){
            return false;
        }
    }

}




