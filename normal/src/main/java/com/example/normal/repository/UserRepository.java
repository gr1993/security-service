package com.example.normal.repository;

import com.example.normal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUsername(String username);
}
