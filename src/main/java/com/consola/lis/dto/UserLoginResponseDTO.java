package com.consola.lis.dto;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private String userName;
    private String role;
    private String id;
    private String name;
    private String lastName;
    private boolean allow;
    private String token;
    private String message;
}
