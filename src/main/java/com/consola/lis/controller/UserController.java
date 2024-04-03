package com.consola.lis.controller;


import com.consola.lis.constans.EndpointConstant;
import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.UserDTO;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.enums.UserRole;
import com.consola.lis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_USER)
public class UserController {

    private final UserService userService;
    @GetMapping("/{userName}")
    public User oneUser(@PathVariable("userName") String userId){
        return userService.getUser(userId);
    }


    @PatchMapping("/role/{username}")
    public ResponseEntity<AuthResponseDTO> changeRole(@PathVariable("username") String username, @RequestBody UserRole userRole){
        return ResponseEntity.ok(userService.changeUserRole(username, userRole));
    }

    @GetMapping("/ldapUser/{username}")
    public ResponseEntity<UserDTO> userLDAP(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getUserLDAP(username));
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username){
         userService.deleteUser(username);
    }
}
