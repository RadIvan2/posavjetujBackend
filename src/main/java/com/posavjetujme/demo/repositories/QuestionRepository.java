package com.posavjetujme.demo.repositories;

import com.posavjetujme.demo.domains.Question;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer>, JpaSpecificationExecutor<Question> {
    List<Question> findByGaleryAvailableTrueAndAnsweredTrue();

    List<Question> findByCategoryId(Integer id);

}