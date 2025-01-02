package com.example.normal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    private String username;

    @Column
    private String role;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;
}
