package com.consola.lis.controller;


import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.model.entity.Category;
import com.consola.lis.service.CategoryService;
import com.fasterxml.jackson.core.JacksonException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping
    public void createCategory(@RequestBody CategoryDTO category) throws JacksonException {
        categoryService.createCategory(category);
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

