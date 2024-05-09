package com.consola.lis.service;

import com.consola.lis.dto.CategoryDTO;
import com.consola.lis.dto.ListAttributeDTO;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.NotExistingException;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.repository.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(categories, result);
    }

    @Test
    void testCreateCategory_Success() throws JsonProcessingException {

        CategoryDTO categoryDTO= new CategoryDTO("Test Category","test",true,new String[]{"Attribute1"},new ListAttributeDTO[]{new ListAttributeDTO()});
        when(categoryRepository.existsByCategoryName("Test Category")).thenReturn(false);
        //act
        categoryService.createCategory(categoryDTO);
        //Assert
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void testCreateCategory_CategoryAlreadyExists()  {

        CategoryDTO categoryDTO= new CategoryDTO("Test Category","test",true,new String[]{"Attribute1"},new ListAttributeDTO[]{new ListAttributeDTO()});
        when(categoryRepository.existsByCategoryName("Test Category")).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> categoryService.createCategory(categoryDTO));

        verify(categoryRepository, never()).save(any());
    }

    @Test
    void testDeleteCategory_CategoryExists() {

        int categoryId = 1;

        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        assertDoesNotThrow(() -> categoryService.deleteCategory(categoryId));
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    void testDeleteCategory_CategoryNotExists() {

        int categoryId = 1;

        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        assertThrows(NotExistingException.class, () -> categoryService.deleteCategory(categoryId));
        verify(categoryRepository, never()).deleteById(categoryId);
    }

    @Test
    void testFindCategory_CategoryExists() {
        String categoryName = "Test";
        Category category = new Category();
        category.setCategoryName(categoryName);

        when(categoryRepository.findByCategoryName(categoryName)).thenReturn(Optional.of(category));

        assertEquals(category, categoryService.findCategory(categoryName));
    }

    @Test
    void testFindCategory_CategoryNotExists() {
        String categoryName = "Test";

        when(categoryRepository.findByCategoryName(categoryName)).thenReturn(Optional.empty());

        assertThrows(NotExistingException.class, () -> categoryService.findCategory(categoryName));
    }

    @Test
    void testGetCategoryNames_CategoriesExist() {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        assertEquals(2, categoryService.getCategoryNames().size());
    }

    @Test
    void testGetCategoryNames_NoCategoriesFound() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NotExistingException.class, () -> categoryService.getCategoryNames());
    }

}