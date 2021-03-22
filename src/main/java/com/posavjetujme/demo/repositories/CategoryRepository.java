package com.posavjetujme.demo.repositories;

import com.posavjetujme.demo.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findById(Integer id);
}
