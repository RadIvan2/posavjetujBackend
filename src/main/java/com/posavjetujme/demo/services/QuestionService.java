package com.posavjetujme.demo.services;

import com.posavjetujme.demo.domains.Category;
import com.posavjetujme.demo.domains.Question;
import com.posavjetujme.demo.repositories.QuestionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private static final Logger LOGGER= LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    private QuestionRepository questionRepository;

    public Question create(Question question) {
        LOGGER.info("SVE RADI MOZDA");
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getAnsweredQuestions() {
        List<Question> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(questionRepository.findByAnswersIsNotNull()));
        return listWithoutDuplicates;
    }

    public List<Question> getUnansweredQuestions() {
        List<Question> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(questionRepository.findByAnswersIsNull()));
        return listWithoutDuplicates;
    }


    /**public List<Question> getQuestionByCategory(Integer id){
        return questionRepository.findByCategoryId(id);
    }*/

    public List<Question> getAnsweredQuestionByCategory(Integer id){
        List<Question> listWithoutDuplicates = new ArrayList<>(
                new HashSet<>(questionRepository.findByCategoryIdAndAndAnswersIsNotNull(id)));
        return listWithoutDuplicates;
    }


    public List<Question> findBySearchSpecification(Specification<Question> questionSpecification) {
        return questionRepository.findAll(questionSpecification);
    }

    public void delete(Integer id) {
        questionRepository.deleteById(id);
    }


    public Question updateQuestion(Integer id, Question question) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if(questionOptional.isPresent()){
            Question questionToBeUpdated = questionOptional.get();
            questionToBeUpdated.setContent(question.getContent());
            questionToBeUpdated.setCreatedAt(question.getCreatedAt());
            questionToBeUpdated.setAnswered(question.isAnswered());
            questionToBeUpdated.setGaleryAvailable(question.isGaleryAvailable());
            questionToBeUpdated.setCategory(question.getCategory());
            return questionRepository.save(questionToBeUpdated);
        }
        return null;
    }

    public Question update(Question question) {return questionRepository.save(question);
    }
}
