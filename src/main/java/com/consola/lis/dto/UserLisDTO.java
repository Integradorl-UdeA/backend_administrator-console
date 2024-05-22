package com.consola.lis.dto;

import com.consola.lis.model.enums.UserRole;
import com.consola.lis.util.deserializer.UserRoleDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLisDTO {

    @JsonProperty("username")
    @JsonAlias("user")
    private String username;

    private String name;

    @JsonProperty("lastname")
    @JsonAlias("lastName")
    private String lastname;

    @JsonDeserialize(using = UserRoleDeserializer.class)
    private UserRole role;

    @JsonProperty("idUser")
    @JsonAlias("id")
    private String idUser;
}
