package com.consola.lis.service;

import com.consola.lis.util.exception.NotExistingException;
import com.consola.lis.jwt.JwtService;
import com.consola.lis.model.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
   /* @Mock
    UserRepository userRepository;

    @Mock
    RestTemplate restTemplate;



    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtService jwtService;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUser_UserNotExists() {
        String username = "nonExistingUsername";
        Optional<User> optionalUser = Optional.empty();
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

    }*/

   /* @Test
    void testChangeUserRole_UserExists() {
        // Arrange
        String username = "testUser";
        UserRole newRole = UserRole.PROFESSOR;
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(jwtService.getToken(user)).thenReturn("mockedToken");

        // Act
        AuthResponseDTO result = userService.changeUserRole(username, newRole);

        // Assert
        assertNotNull(result);
        assertEquals("mockedToken", result.getToken());
        assertEquals(newRole, user.getRole());
        verify(userRepository, times(1)).save(user);
    }*/

    /*@Test
    void testChangeUserRole_UserNotExists() {
        // Arrange
        String username = "nonExistingUser";
        UserRole newRole = UserRole.PROFESSOR;
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotExistingException.class, () -> userService.changeUserRole(username, newRole));
        verify(userRepository, never()).save(any());
    }



    @Test
    void testChangeRole() {
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("1006");

        UserDTO result = userService.changeRole(userDTO);

        assertNotNull(result);
        assertEquals(UserRole.AUXADMI.toString(), result.getRole());
    }

    @Test
    void testGetUserLDAP() {
        // Arrange
        String username = "testUser";
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("1005");
        ResponseEntity<UserDTO> responseEntity = ResponseEntity.ok(userDTO);
        when(restTemplate.getForEntity(anyString(), eq(UserDTO.class), eq(username))).thenReturn(responseEntity);

        // Act
        UserDTO result = userService.getUserLDAP(username);

        // Assert
        assertNotNull(result);
        assertEquals(userDTO.getRole(), result.getRole());
    }

    @Test
    void testGetUserLDAP_ResponseNull() {
        // Arrange
        String username = "nonExistingUser";
        ResponseEntity<UserDTO> responseEntity = ResponseEntity.ok(null);
        when(restTemplate.getForEntity(anyString(), eq(UserDTO.class), eq(username))).thenReturn(responseEntity);

        // Act and Assert
        assertThrows(NotExistingException.class, () -> userService.getUserLDAP(username));
    }
*/
}