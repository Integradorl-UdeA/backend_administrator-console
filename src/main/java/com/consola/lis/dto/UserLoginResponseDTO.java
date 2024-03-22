package com.consola.lis.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class UserLoginResponseDTO {
    private boolean allow;
    private String user;
    private String role;
    private String id;
    private String name;
    private String lastName;
    private String token;
    private String message;
}
