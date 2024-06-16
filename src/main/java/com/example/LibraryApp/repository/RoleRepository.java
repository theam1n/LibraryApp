package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
