package com.example.normal.repository;

import com.example.normal.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtpRepository extends JpaRepository<Otp, String> {

    List<Otp> findByUsername(String username);
}
