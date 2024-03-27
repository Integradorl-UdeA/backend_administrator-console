package com.consola.lis.dto;

import com.consola.lis.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    UserRole role;
    private String id;
    private String name;
    private String lastname;
}
