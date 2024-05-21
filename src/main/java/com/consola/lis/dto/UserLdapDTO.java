package com.consola.lis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLdapDTO {
    @JsonProperty("user")
    private String username;
    private String name;
    @JsonProperty("lastName")
    private String lastname;
    private String role;
    @JsonProperty("id")
    private String idUser;
}
