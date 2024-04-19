package com.consola.lis.service;

import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.RegisterRequestDTO;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.enums.UserRole;
import com.consola.lis.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class RegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterService registerService;

    @BeforeEach
    void setUp() {
         MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_NewUser_SuccessfullyRegistered() {
        // Arrange
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername("testUser");
        registerRequestDTO.setPassword("testPassword");
        registerRequestDTO.setId("testId");
        registerRequestDTO.setName("Test");
        registerRequestDTO.setLastname("User");

        when(userRepository.findByUsernameOrId("testUser", "testId")).thenReturn(Optional.empty());
        when(userRepository.findByUsernameAndId("testUser", "testId")).thenReturn(Optional.empty());

        User savedUser = User.builder()
                .username(registerRequestDTO.getUsername())
                .role(UserRole.AUXADMI)
                .id(registerRequestDTO.getId())
                .name(registerRequestDTO.getName())
                .lastname(registerRequestDTO.getLastname())
                .password("encodedPassword")
                .build();

        when(passwordEncoder.encode(registerRequestDTO.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.getToken(savedUser)).thenReturn("testToken");

        // Act
        AuthResponseDTO responseDTO = registerService.register(registerRequestDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals("testToken", responseDTO.getToken());
        verify(userRepository, times(1)).findByUsernameOrId("testUser", "testId");
        verify(userRepository, times(1)).findByUsernameAndId("testUser", "testId");
        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).getToken(savedUser);
    }

    @Test

    void testRegister_UserAlreadyExists_ConflictExceptionThrown() {
        // Arrange
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername("armando.coco");
        registerRequestDTO.setPassword("testPassword");
        registerRequestDTO.setId("033");
        registerRequestDTO.setName("Test");
        registerRequestDTO.setLastname("User");

        when(userRepository.findByUsernameOrId(registerRequestDTO.getUsername(), registerRequestDTO.getId())).thenReturn(Optional.of(new User()));
        when(userRepository.findByUsernameAndId(registerRequestDTO.getUsername(), registerRequestDTO.getId())).thenReturn(Optional.of(new User()));


        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> registerService.register(registerRequestDTO));
        verify(userRepository, times(1)).findByUsernameOrId(registerRequestDTO.getUsername(), registerRequestDTO.getId());
        verify(userRepository, never()).save(any(User.class));
        verify(jwtService, never()).getToken(any(User.class));

        assertThrows(AlreadyExistsException.class, () -> registerService.register(registerRequestDTO));
        verify(userRepository, times(2)).findByUsernameAndId(registerRequestDTO.getUsername(), registerRequestDTO.getId());
        verify(userRepository, never()).save(any(User.class));
        verify(jwtService, never()).getToken(any(User.class));
    }
}