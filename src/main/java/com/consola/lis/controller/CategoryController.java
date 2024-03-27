package com.consola.lis.controller;


import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.model.entity.Category;
import com.consola.lis.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/console-lis/auth/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping
    public List<Category> categories(){
        return categoryService.getAllCategories();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createCategory(@RequestBody CategoryDTO category) {
        categoryService.createCategory(category);
    }

    @PutMapping("/{categoryName}")
    public void updateCategory(@PathVariable("categoryName") String categoryName, CategoryDTO category){
        categoryService.updateCategory(categoryName, category);

    }

    @DeleteMapping("/{categoryName}")
    public void deleteCategory(@PathVariable("categoryName") String categoryName){
        categoryService.deleteCategory(categoryName);
    }

    @GetMapping("/{category}")
    public Category findCategory(@PathVariable("category") String categoryName){
        return categoryService.findCategory(categoryName);
    }
}

