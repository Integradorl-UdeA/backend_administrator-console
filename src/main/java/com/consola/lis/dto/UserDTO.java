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
public class UserDTO {
    @JsonProperty("user")
    private String username;
    String role;
    private String id;
    private String name;
    @JsonProperty("lastName")
    private String lastname;
}
