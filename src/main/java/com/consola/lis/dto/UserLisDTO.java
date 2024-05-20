package com.consola.lis.dto;

import com.consola.lis.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLisDTO {

    private String username;
    private String name;
    private String lastname;
    private UserRole role;
    private String idUser;
}
