package com.posavjetujme.demo.controllers;

import com.posavjetujme.demo.domains.Question;
import com.posavjetujme.demo.domains.QuestionHasUser;
import com.posavjetujme.demo.services.QuestionHasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question-user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuestionHasUserController {

    @Autowired
    private QuestionHasUserService questionHasUserService;

    @GetMapping
    public ResponseEntity<List<QuestionHasUser>> getQuestion(){
        return new ResponseEntity<>(questionHasUserService.getAllQuestions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QuestionHasUser>createQHU(@ModelAttribute QuestionHasUser questionHasUser){

        if(questionHasUser.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        QuestionHasUser savedQuestion = questionHasUserService.create(questionHasUser);
        return new ResponseEntity<>(savedQuestion,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<QuestionHasUser> updateWithPath(@PathVariable Integer id,
                                                          @RequestBody QuestionHasUser questionHasUser){
        QuestionHasUser updatedQHU= questionHasUserService.updateWithPath(id,questionHasUser);
        if(updatedQHU == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedQHU,HttpStatus.OK);
    }
}
