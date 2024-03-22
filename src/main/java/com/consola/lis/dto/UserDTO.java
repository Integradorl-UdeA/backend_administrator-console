package com.consola.lis.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
public class UserDTO {
    private String userName;
    private String role;
    private String id;
    private String name;
    private String lastName;
}
