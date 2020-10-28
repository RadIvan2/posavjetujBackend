package com.posavjetujme.demo.services;

import com.posavjetujme.demo.domains.Answer;
import com.posavjetujme.demo.domains.Question;
import com.posavjetujme.demo.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
        private AnswerRepository answerRepository;

        public Answer create(Answer answer) {
            return answerRepository.save(answer);
        }

        public List<Answer> getAllAnswers() {
                return answerRepository.findAll();
        }

    public void delete(Integer id) {
            answerRepository.deleteById(id);
    }

    public List<Answer> getAnswersByQuestion(Integer questionId){
            return answerRepository.findAllByQuestionId(questionId);
    }
    public List<Answer> getAnswersByApproved(Integer approvedId){
        return answerRepository.findAllByApprovedByIdId(approvedId);
    }

    public List<Answer> getAnswersByCreted(Integer creatorId){
        return answerRepository.findAllByCreatorId(creatorId);
    }

    public List<Answer> getNotApproved() {
            return answerRepository.findAllByApprovedFalse();
    }
}
