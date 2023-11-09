package com.securityJWT.JWT.entity;

import com.securityJWT.JWT.util.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
