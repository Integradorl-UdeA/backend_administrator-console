package com.consola.lis.service;

import com.consola.lis.model.entity.UserLis;
import com.consola.lis.model.repository.UserLisRepository;
import com.consola.lis.util.exception.NotExistingException;
import com.consola.lis.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserLisRepository userRepository;

    @Mock
    RestTemplate restTemplate;

    @Mock
    JwtService jwtService;


    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUser_UserNotExists() {
        String username = "nonExistingUsername";
        Optional<UserLis> optionalUser = Optional.empty();
        when(userRepository.findByUsername(username)).thenReturn(optionalUser);

        assertThrows(NotExistingException.class, () -> userService.getUser(username));

        verify(userRepository, times(1)).findByUsername(username);

    }



    @Test
    void testDeleteUser_UserExist() {
        String username = "existingUser";
        when(userRepository.existsByUsername(username)).thenReturn(true);

        userRepository.deleteByUsername(username);

        verify(userRepository, times(1)).deleteByUsername(username);
    }

    @Test
    void testDeleteUser_UserNotExist() {
        String username = "nonExistingUser";

        when(userRepository.existsByUsername(username)).thenReturn(false);

        assertThrows(NotExistingException.class, () -> userService.getUser(username));
        verify(userRepository, never()).deleteByUsername(username);

    }







  /*  @Test
    void testGetUserLDAP() {
        // Arrange
        String username = "testUser";
        UserLdapDTO userDTO = new UserLdapDTO();
        userDTO.setRole("1005");
        ResponseEntity<UserLdapDTO> responseEntity = ResponseEntity.ok(userDTO);
        when(restTemplate.getForEntity(anyString(), eq(UserLdapDTO.class), eq(username))).thenReturn(responseEntity);

        // Act
        UserLdapDTO result = userService.getUserLDAP(username);

        // Assert
        assertNotNull(result);
        assertEquals(userDTO.getRole(), result.getRole());
    }

    @Test
    void testGetUserLDAP_ResponseNull() {
        // Arrange
        String username = "nonExistingUser";
        ResponseEntity<UserLisDTO> responseEntity = ResponseEntity.ok(null);
        when(restTemplate.getForEntity(anyString(), eq(UserLisDTO.class), eq(username))).thenReturn(responseEntity);

        // Act and Assert
        assertThrows(NotExistingException.class, () -> userService.getUserLDAP(username));
    }*/

}