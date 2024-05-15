package com.consola.lis.controller;


import com.consola.lis.util.constans.ApiDescription;
import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.model.entity.Category;
import com.consola.lis.service.CategoryService;
import com.fasterxml.jackson.core.JacksonException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Manage categories", description = ApiDescription.DESCRIPTION_CONTROLLER_CATEGORIES)
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary=ApiDescription.DESCRIPTION_ALL_CATEGORIES)
    @GetMapping
    public List<Category> categories(){
        return categoryService.getAllCategories();
    }

    @Operation(summary = ApiDescription.DESCRIPTION_CREATE_CATEGORY)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO category) throws JacksonException {
        return  ResponseEntity.ok(categoryService.createCategory(category));
    }


    @Operation(summary = ApiDescription.DESCRIPTION_DELETE_CATEGORY)
    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_CATEGORY)
    public void deleteCategory(@PathVariable("categoryId") int categoryId){
        categoryService.deleteCategory(categoryId);
    }

    @Operation(summary = ApiDescription.DESCRIPTION_ONE_CATEGORY)
    @GetMapping(EndpointConstant.ENDPOINT_ONE_CATEGORY)
    public ResponseEntity<Category> findCategory(@PathVariable("categoryId") String categoryId){
        return ResponseEntity.ok(categoryService.findCategory(categoryId));
    }

    @Operation(summary = ApiDescription.DESCRIPTION_ALL_NAMES_CATEGORIES)
    @GetMapping(EndpointConstant.ENDPOINT_ALL_NAMES_CATEGORIES)
    public List<String> getCategoryNames() {
        return categoryService.getCategoryNames();
    }
}

