package com.consola.lis.model.entity;

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
@Table(name="user_hello_lis")
public class UserHelloLis  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_hello_lis")
    private Integer id;

    private String password;

    @OneToOne
    @JoinColumn(name = "id_user_lis", nullable = false)
    private UserLis userLis;



}
