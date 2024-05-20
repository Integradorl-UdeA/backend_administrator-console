package com.consola.lis.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserHelloLisDTO extends UserLisDTO {

    @NotBlank(message = "Password is required")
    private String password;
    private String idUser;

}
