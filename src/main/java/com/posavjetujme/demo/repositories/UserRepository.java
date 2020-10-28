package com.posavjetujme.demo.repositories;

import com.posavjetujme.demo.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
