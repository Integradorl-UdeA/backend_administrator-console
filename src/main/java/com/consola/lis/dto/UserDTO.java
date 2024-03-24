package com.consola.lis.dto;

import com.consola.lis.util.UserRole;
import lombok.*;

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
