package com.example.normal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    private String username;

    @Column
    private String password;

    @Column
    private Integer age;

    @OneToOne(
        mappedBy = "user",
        cascade = CascadeType.ALL
    )
    private Role role;
}
