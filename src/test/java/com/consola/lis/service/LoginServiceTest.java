package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.LoginRequestDTO;
import com.consola.lis.exception.UserAuthenticationException;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.enums.UserRole;
import com.consola.lis.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@AllArgsConstructor
class LoginServiceTest {

    private final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final JwtService jwtService = mock(JwtService.class);

    private final LoginService authService = new LoginService(authenticationManager, userRepository, jwtService);

    @Test
    void login() {
        LoginRequestDTO loginRequest = new LoginRequestDTO("example_user", "password");
        User user = new User();
        user.setId("1");
        user.setUsername("example_user");
        user.setName("John");
        user.setLastname("Doe");
        user.setPassword("password");
        user.setRole(UserRole.AUXADMI);

        String token = "generatedToken";

        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(java.util.Optional.of(user));
        when(jwtService.getToken(user)).thenReturn(token);

        // Act
        AuthResponseDTO response = authService.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(token, response.getToken());
    }

    @Test
    void testLogin_AuthenticationFailure() {
        // Arrange
        LoginRequestDTO loginRequest = new LoginRequestDTO("invalidUsername", "invalidPassword");

        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserAuthenticationException.class, () -> authService.login(loginRequest));
    }
}