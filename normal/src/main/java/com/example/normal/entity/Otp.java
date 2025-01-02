package com.example.normal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Otp {

    @Id
    private String username;

    @Column
    private String code;
}
