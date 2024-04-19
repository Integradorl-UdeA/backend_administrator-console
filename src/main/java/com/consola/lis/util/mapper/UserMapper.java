package com.consola.lis.util.mapper;

import com.consola.lis.dto.UserDTO;
import com.consola.lis.model.entity.User;




public class UserMapper {
    private UserMapper() {
    }
    public static UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .role(String.valueOf(user.getRole()))
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .build();
    }
}

