package com.consola.lis.service;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.dto.UserLisDTO;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.enums.UserRole;
import com.consola.lis.model.repository.UserLisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;



class RegisterServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterService registerService;

    @Mock
    private UserLisRepository userLisRepository;

    @BeforeEach
    void setUp() {
         MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_NewUserLis_SuccessfullyRegistered() {
        // Arrange
        UserLisDTO registerRequestDTO = new UserLisDTO();
        registerRequestDTO.setUsername("testUser");
        registerRequestDTO.setIdUser("testId");
        registerRequestDTO.setName("Test");
        registerRequestDTO.setLastname("User");

        when(userLisRepository.existsById("testUser")).thenReturn(false);
        when(userLisRepository.existsByUsername("testId")).thenReturn(false);

        UserLis savedUser = UserLis.builder()
                .username(registerRequestDTO.getUsername())
                .role(UserRole.AUXADMI)
                .idUser(registerRequestDTO.getIdUser())
                .name(registerRequestDTO.getName())
                .lastname(registerRequestDTO.getLastname())
                .build();


        when(userLisRepository.save(any(UserLis.class))).thenReturn(savedUser);
        when(jwtService.getToken(savedUser)).thenReturn("testToken");

        // Act
        UserLis responseUserLis = registerService.registerUserLis(registerRequestDTO);

        // Assert
        assertNotNull(responseUserLis);
        verify(userLisRepository, times(1)).existsByUsername("testUser");
        verify(userLisRepository, times(1)).existsById("testId");
        verify(userLisRepository, times(1)).save(any(UserLis.class));

    }

    @Test

    void testRegister_UserAlreadyExists_ConflictExceptionThrown() {
        // Arrange
        UserLisDTO registerRequestDTO = new UserLisDTO();
        registerRequestDTO.setUsername("armando.coco");
        registerRequestDTO.setIdUser("033");
        registerRequestDTO.setName("Test");
        registerRequestDTO.setLastname("User");

        when(userLisRepository.existsByUsername(registerRequestDTO.getUsername())).thenReturn(true);
        when(userLisRepository.existsByUsername(registerRequestDTO.getIdUser())).thenReturn(true);


        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> registerService.registerUserLis(registerRequestDTO));
        verify(userLisRepository, times(1)).existsByUsername(registerRequestDTO.getUsername());
        verify(userLisRepository, never()).save(any(UserLis.class));


    }
}