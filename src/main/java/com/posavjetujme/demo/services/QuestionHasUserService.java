package com.posavjetujme.demo.services;

import com.posavjetujme.demo.domains.QuestionHasUser;
import com.posavjetujme.demo.repositories.QuestionHasUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionHasUserService {

    @Autowired
    private QuestionHasUserRepository questionHasUserRepository;

    public List<QuestionHasUser> getAllQuestions() {
        return questionHasUserRepository.findAll();
    }

    public QuestionHasUser create(QuestionHasUser questionHasUser) {
        return questionHasUserRepository.save(questionHasUser);
    }

    public QuestionHasUser updateWithPath(Integer id, QuestionHasUser questionHasUser) {
        Optional<QuestionHasUser> QHUtoBeUpdatedOpt = questionHasUserRepository.findById(id);
        if(QHUtoBeUpdatedOpt.isPresent()){
            QuestionHasUser QHUToBeUpdated = QHUtoBeUpdatedOpt.get();
            QHUToBeUpdated.setAssigned(questionHasUser.isAssigned());
            QHUToBeUpdated.setDateAssigned(questionHasUser.getDateAssigned());
            QHUToBeUpdated.setQuestion(questionHasUser.getQuestion());
            QHUToBeUpdated.setUser(questionHasUser.getUser());
            return questionHasUserRepository.save(QHUToBeUpdated);
        }
        return null;
    }
}
