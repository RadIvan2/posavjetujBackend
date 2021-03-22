package com.posavjetujme.demo.domains;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean approved = false;

    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name = "approved_by_id")
    private User approvedById;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JsonBackReference//(value="cretor-question")
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JsonBackReference(value="answ-question")
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;


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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

   public User getApprovedById() {
        return approvedById;
    }

    public void setApprovedById(User approvedById) {
        this.approvedById = approvedById;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }




}
