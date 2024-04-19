package com.consola.lis.controller;


import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.dto.AuthResponseDTO;
import com.consola.lis.dto.UserDTO;
import com.consola.lis.model.enums.UserRole;
import com.consola.lis.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Manage users", description = "something")
@RequiredArgsConstructor
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_USER)
public class UserController {

    private final UserService userService;
    @GetMapping(EndpointConstant.ENDPOINT_ONE_USER)
    public ResponseEntity<UserDTO> oneUser(@PathVariable("username") String userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }


    @Operation(summary = "This method is used to login the options valid are ADMIN, AUXPROG, AUXADMI")
    @PatchMapping(EndpointConstant.ENDPOINT_CHANGE_ROLE_USER)
    public ResponseEntity<AuthResponseDTO> changeRole(@PathVariable("username") String username, @RequestBody UserRole userRole){
        return ResponseEntity.ok(userService.changeUserRole(username, userRole));
    }

    @GetMapping(EndpointConstant.ENDPOINT_USER_LDAP)
    public ResponseEntity<UserDTO> userLDAP(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getUserLDAP(username));
    }

    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_USER)
    public void deleteUser(@PathVariable("username") String username){
         userService.deleteUser(username);
    }
}
