package com.posavjetujme.demo.search;

public class QuestionSearch {
    private Boolean questionAnswered;
    private Boolean questionGaleryAvailable;
    private String categoryName;

    public Boolean getQuestionAnswered() {
        return questionAnswered;
    }

    public void setQuestionAnswered(Boolean questionAnswered) {
        this.questionAnswered = questionAnswered;
    }

    public Boolean getQuestionGaleryAvailable() {
        return questionGaleryAvailable;
    }

    public void setQuestionGaleryAvailable(Boolean questionGaleryAvailable) {
        this.questionGaleryAvailable = questionGaleryAvailable;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
