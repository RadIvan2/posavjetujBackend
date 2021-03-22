package com.posavjetujme.demo.specs;

import com.posavjetujme.demo.domains.Category;
import com.posavjetujme.demo.domains.Question;
import com.posavjetujme.demo.search.QuestionSearch;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionSearchSpecification implements Specification<Question> {

    private QuestionSearch questionSearch;

    public QuestionSearchSpecification(QuestionSearch questionSearch){
        this.questionSearch=questionSearch;
    }

    @Override
    public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        filterQuestionAnswered(root,criteriaBuilder,predicates);
        filterQuestionGaleryAvailable(root,criteriaBuilder,predicates);
        filterCategory(root,criteriaBuilder,predicates);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void filterQuestionAnswered(Root<Question> root,CriteriaBuilder criteriaBuilder,List<Predicate> predicates){
        if(questionSearch.getQuestionAnswered() != null){
            predicates.add(criteriaBuilder.equal(root.get("answered"),questionSearch.getQuestionAnswered()));
        }
    }


    private void filterCategory(Root<Question> root,CriteriaBuilder criteriaBuilder,List<Predicate> predicates){
        if(questionSearch.getCategoryName() != null){
            Join<Question, Category> questionCategoryJoin = root.join("category");
            predicates.add(criteriaBuilder.equal(questionCategoryJoin.get("name"), questionSearch.getCategoryName()));
        }
    }

    private void filterQuestionGaleryAvailable(Root<Question> root,CriteriaBuilder criteriaBuilder,List<Predicate> predicates){
        if(questionSearch.getQuestionGaleryAvailable() != null){
            predicates.add(criteriaBuilder.equal(root.get("galeryAvailable"),questionSearch.getQuestionGaleryAvailable()));
        }
    }





}
