package com.posavjetujme.demo.repositories;

import com.posavjetujme.demo.domains.QuestionHasUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionHasUserRepository extends JpaRepository<QuestionHasUser,Integer> {
}
