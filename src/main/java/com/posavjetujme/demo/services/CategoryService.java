package com.posavjetujme.demo.services;

import com.posavjetujme.demo.domains.Category;
import com.posavjetujme.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Integer id){
        return categoryRepository.findById(id);
    }

    public Category updateCategory(Integer id, Category category) {
        Optional<Category> categoryOptional=categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category categoryToBeUpdated = categoryOptional.get();
            categoryToBeUpdated.setName(category.getName());
            categoryToBeUpdated.setDescription(category.getDescription());
            categoryToBeUpdated.setQuestions(category.getQuestions());
            return categoryRepository.save(categoryToBeUpdated);
        }
        return null;
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
