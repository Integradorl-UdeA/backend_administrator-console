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


}