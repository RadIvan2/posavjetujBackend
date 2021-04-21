package com.posavjetujme.demo.domains;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "creator", targetEntity = Answer.class,cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();
/*
    @JsonManagedReference
    @OneToMany(mappedBy = "approvedById", targetEntity = Answer.class,cascade = CascadeType.ALL)
    private List<Answer> approvedAnswers = new ArrayList<>();*/

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles= new HashSet<>();

   /* @JsonManagedReference
    @OneToMany(mappedBy = "user", targetEntity = QuestionHasUser.class)
    private List<QuestionHasUser> questionHasUsers = new ArrayList<>();*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
/*
    public List<Answer> getApprovedAnswers() {
        return approvedAnswers;
    }

    public void setApprovedAnswers(List<Answer> approvedAnswers) {
        this.approvedAnswers = approvedAnswers;
    }*/

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

/* public List<QuestionHasUser> getQuestionHasUsers() {
        return questionHasUsers;
    }

    public void setQuestionHasUsers(List<QuestionHasUser> questionHasUsers) {
        this.questionHasUsers = questionHasUsers;
    }*/
}
