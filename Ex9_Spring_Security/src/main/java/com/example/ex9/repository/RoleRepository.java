package com.example.ex9.repository;

import com.example.ex9.model.entity.RoleName;
import com.example.ex9.model.entity.impl.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
