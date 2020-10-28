package com.posavjetujme.demo.repositories;

import com.posavjetujme.demo.domains.Answer;
import com.posavjetujme.demo.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    List<Answer> findAllByQuestionId(Integer questionId);
    List<Answer> findAllByApprovedByIdId(Integer id);
    List<Answer> findAllByCreatorId(Integer id);
    List<Answer> findAllByApprovedFalse();

}
