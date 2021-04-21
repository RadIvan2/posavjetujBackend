package com.posavjetujme.demo.controllers;


import com.posavjetujme.demo.domains.Category;
import com.posavjetujme.demo.domains.Question;
import com.posavjetujme.demo.search.QuestionSearch;
import com.posavjetujme.demo.services.QuestionService;
import com.posavjetujme.demo.specs.QuestionSearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/question", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        if(question.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Question savedQuestion = questionService.
                create(question);
        return new ResponseEntity<>(savedQuestion,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Question>> getQuestion(){
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping(value = "/answered")
    public ResponseEntity<List<Question>> getAnswewredQuestion(){
        return new ResponseEntity<>(questionService.getAnsweredQuestions(),HttpStatus.OK);
    }

    @GetMapping(value = "/unanswered")
    public ResponseEntity<List<Question>> getUnanswewredQuestion(){
        return new ResponseEntity<>(questionService.getUnansweredQuestions(),HttpStatus.OK);
    }

    @GetMapping("/answered-and-category-{id}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable Integer id){
        return new ResponseEntity<>(questionService.getAnsweredQuestionByCategory(id),HttpStatus.OK);
    }

    @GetMapping("/answer-{id}")
    public ResponseEntity<List<Question>> geQuestionByAnswerId(@PathVariable Integer id){
        return new ResponseEntity<>(questionService.findByAnswerId(id),HttpStatus.OK);
    }

    @GetMapping(value = "/by-specs")
    public ResponseEntity<List<Question>> getBySpecs(QuestionSearch questionSearch){
        Specification<Question> questionSpecification = new QuestionSearchSpecification(questionSearch);
        List<Question> questions = questionService.findBySearchSpecification(questionSpecification);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question question){
        Question updatedQuestion = questionService.updateQuestion(id,question);
        if(updatedQuestion == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedQuestion,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Question> update(@RequestBody Question question){
        Question updatedDepartment = questionService.update(question);
        return new ResponseEntity<>(updatedDepartment,HttpStatus.OK);
    }
}

