package com.example.normal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Integer age;
}
