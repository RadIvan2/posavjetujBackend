package com.posavjetujme.demo.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @Column(nullable = false)
    private boolean answered = false;

    @Column(name = "galery_available", nullable = false)
    private boolean galeryAvailable = false;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonBackReference
    @OneToMany(mappedBy = "user", targetEntity = QuestionHasUser.class)
    private List<QuestionHasUser> questionHasUsers = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isGaleryAvailable() {
        return galeryAvailable;
    }

    public void setGaleryAvailable(boolean galeryAvailable) {
        this.galeryAvailable = galeryAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}