package com.consola.lis.service;


import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.exception.AlreadyExistsException;
import com.consola.lis.exception.NotExistingException;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.repository.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(CategoryDTO categoryRequest) throws JsonProcessingException {
        boolean existingCategory = categoryRepository.existsByCategoryName(categoryRequest.getCategoryName());

        if (existingCategory) {
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "Category already exists");
        } else {
            String attributesJson = objectMapper.writeValueAsString(categoryRequest.getAttributes());
            String listAttributesJson = objectMapper.writeValueAsString(categoryRequest.getListAttributes());
            Category category = Category.builder()
                    .categoryName(categoryRequest.getCategoryName().toLowerCase())
                    .quantizable(categoryRequest.getQuantizable())
                    .attributes(attributesJson)
                    .listAttributes(listAttributesJson)
                    .build();

            categoryRepository.save(category);
        }


    }

    @Transactional
    public void deleteCategory(String categoryName) {

        if (existCategory(categoryName)) {
            categoryRepository.deleteByCategoryName(categoryName);
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the category whit name " + categoryName + " not exist");
        }
    }

    public Category findCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the category whit name " + categoryName + " not exist");
        }
    }

    public boolean existCategory(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }


    public List<String> getCategoryNames() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories.stream()
                    .map(Category::getCategoryName)
                    .toList();
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, "No categories found.");
        }
    }


}




