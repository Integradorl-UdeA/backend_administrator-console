package com.consola.lis.service;


import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.exception.UserAlreadyExistsException;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.User;
import com.consola.lis.model.repository.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(CategoryDTO categoryRequest) {
        boolean existingCategory = categoryRepository.existsByCategoryName(categoryRequest.getCategoryName());


        if (existingCategory) {
            throw new UserAlreadyExistsException("409", HttpStatus.CONFLICT, "Category already exists");
        } else {
            Category category = Category.builder()
                    .categoryName(categoryRequest.getCategoryName().toLowerCase())
                    .quantizable(categoryRequest.getQuantizable())
                    .attributes(categoryRequest.getAttributes())
                    .listAttributes(categoryRequest.getListAttributes())
                    .build();
            categoryRepository.save(category);
        }


    }

    public void updateCategory(String categoryName, CategoryDTO categoryRequest) {
        if (categoryRepository.existsByCategoryName(categoryName)) {
            Category category = Category.builder()
                    .categoryName(categoryRequest.getCategoryName().toLowerCase())
                    .quantizable(categoryRequest.getQuantizable())
                    .attributes(categoryRequest.getAttributes())
                    .listAttributes(categoryRequest.getListAttributes())
                    .build();
            categoryRepository.save(category);

        } else {
            throw new IllegalArgumentException("La categoría con el ID " + categoryName + " no existe");
        }
    }

    @Transactional
    public void deleteCategory(String categoryName) {

        if (categoryRepository.existsByCategoryName(categoryName)) {
            categoryRepository.deleteByCategoryName(categoryName);
        } else {
            throw new IllegalArgumentException(" the category whit ID " + categoryName + " not exist");
        }
    }

    public Category findCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new IllegalArgumentException("No exist a category whit this name");
        }
    }
}




