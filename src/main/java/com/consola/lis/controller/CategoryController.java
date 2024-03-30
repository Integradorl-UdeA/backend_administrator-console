package com.consola.lis.controller;


import com.consola.lis.constans.EndpointConstant;
import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.model.entity.Category;
import com.consola.lis.service.CategoryService;
import com.fasterxml.jackson.core.JacksonException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(EndpointConstant.ENDPOINT_CATEGORY)
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


    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_CATEGORY)
    public void deleteCategory(@PathVariable("categoryName") String categoryName){
        categoryService.deleteCategory(categoryName);
    }

    @GetMapping(EndpointConstant.ENDPOINT_ONE_CATEGORY)
    public Category findCategory(@PathVariable("categoryName") String categoryName){
        return categoryService.findCategory(categoryName);
    }

    @GetMapping(EndpointConstant.ENDPOINT_ALL_NAMES_CATEGORYS)
    public List<String> getCategoryNames() {
        return categoryService.getCategoryNames();
    }
}

