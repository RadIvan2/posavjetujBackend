package com.posavjetujme.demo.controllers;

import com.posavjetujme.demo.domains.Answer;
import com.posavjetujme.demo.domains.Question;
import com.posavjetujme.demo.services.AnswerService;
import com.posavjetujme.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/answer", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer){
        if(answer.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(answerService.create(answer),HttpStatus.CREATED);
    }
     @GetMapping
    public ResponseEntity<List<Answer>> getAnswer(){
        return new ResponseEntity<>(answerService.getAllAnswers(),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        answerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/question{id}")
    public ResponseEntity<List<Answer>> getAnswByQuestionId(@PathVariable Integer id){
        return new ResponseEntity<>(answerService.getAnswersByQuestion(id),HttpStatus.OK);
    }

    @GetMapping(value = "/approved{id}")
    public ResponseEntity<List<Answer>> getAnswByApprovedId(@PathVariable Integer id){
        return new ResponseEntity<>(answerService.getAnswersByApproved(id),HttpStatus.OK);
    }

    @GetMapping(value = "/creator{id}")
    public ResponseEntity<List<Answer>> getAnswByCreatorId(@PathVariable Integer id){
        return new ResponseEntity<>(answerService.getAnswersByCreted(id),HttpStatus.OK);
    }

    @GetMapping(value="/not-approved")
    public ResponseEntity<List<Answer>> getNotApproved(){
        return new ResponseEntity<>(answerService.getNotApproved(),HttpStatus.OK);
    }


}
