package com.example.normal.repository;

import com.example.normal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    List<Role> findByUsername(String username);
}
