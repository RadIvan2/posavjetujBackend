package com.posavjetujme.demo.repositories;

import com.posavjetujme.demo.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
