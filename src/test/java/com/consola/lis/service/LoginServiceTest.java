package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.model.entity.UserHelloLis;
import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.repository.UserLisRepository;
import com.consola.lis.util.exception.UserAuthenticationException;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.enums.UserRole;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@AllArgsConstructor
class LoginServiceTest {

    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private final UserLisRepository userLisRepository = mock(UserLisRepository.class);
    private final JwtService jwtService = mock(JwtService.class);

    private final LoginService authService = new LoginService(authenticationManager, userLisRepository, jwtService);


    @Test
    void testLogin() {
        LoginRequestDTO loginRequest = new LoginRequestDTO("example_user", "password");
        UserLis user = new UserLis();
        user.setIdUser("1");
        user.setUsername("example_user");
        user.setName("John");
        user.setLastname("Doe");
        user.setRole(UserRole.AUXADMI);

        UserHelloLis userHelloLis = new UserHelloLis();
        userHelloLis.setPassword("password");
        userHelloLis.setUserLis(user);
        String token = "generatedToken";

        when(userLisRepository.findByUsername(loginRequest.getUsername())).thenReturn(java.util.Optional.of(user));
        when(jwtService.getToken(user)).thenReturn(token);


        AuthResponseDTO response = authService.login(loginRequest);

        assertNotNull(response);
        assertEquals(token, response.getToken());
    }

    @Test
    void testLogin_AuthenticationFailure() {

        LoginRequestDTO loginRequest = new LoginRequestDTO("invalidUsername", "invalidPassword");

        when(userLisRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.empty());

        assertThrows(UserAuthenticationException.class, () -> authService.login(loginRequest));
    }
}