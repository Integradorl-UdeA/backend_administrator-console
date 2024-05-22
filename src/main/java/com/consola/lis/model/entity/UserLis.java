package com.consola.lis.model.entity;

import com.consola.lis.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_lis", uniqueConstraints = {@UniqueConstraint(columnNames={"username"})})
public class UserLis {

    private String username;

    @Id
    @Column(name="id_user_lis")
    private String idUser;

    @Enumerated(EnumType.STRING)
    UserRole role;
    private String name;
    private String lastname;

}