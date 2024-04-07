package com.consola.lis.service;

import com.consola.lis.dto.GeneralItemDTO;
import com.consola.lis.dto.QuantizableItemDTO;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.IllegalParameterInRequest;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.GeneralItem;
import com.consola.lis.model.entity.QuantizableItem;
import com.consola.lis.model.repository.CategoryRepository;
import com.consola.lis.model.repository.GeneralItemRepository;
import com.consola.lis.model.repository.QuantizableItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class InventoryItemServiceTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private GeneralItemRepository generalItemRepository;

    @Mock
    private QuantizableItemRepository quantizableItemRepository;

    @Mock
    private  CategoryRepository categoryRepository;

    @InjectMocks
    private InventoryItemService  inventoryItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllGeneralItems() {
        List<GeneralItem> mockedList = Arrays.asList(new GeneralItem(), new GeneralItem());
        when(generalItemRepository.findAll()).thenReturn(mockedList);

        List<GeneralItem> result = inventoryItemService.getAllGeneralItems();

        assertEquals(mockedList, result);
    }

    @Test
    void testGetAllQuantizableItems() {
        List<QuantizableItem> mockedList = Arrays.asList(new QuantizableItem(), new QuantizableItem());
        when(quantizableItemRepository.findAll()).thenReturn(mockedList);

        List<QuantizableItem> result = inventoryItemService.getAllQuantizableItems();

        assertEquals(mockedList, result);
    }

    @Test
    void testDeleteItem() {
        String itemId = "123";
        when(generalItemRepository.existsById(itemId)).thenReturn(true);

        assertDoesNotThrow(() -> inventoryItemService.deleteItem(itemId));

        verify(generalItemRepository, times(1)).deleteById(itemId);
    }

    @Test
    void testFindGeneralItem() {
        String itemId = "123";
        GeneralItem mockItem = new GeneralItem();
        when(generalItemRepository.findById(itemId)).thenReturn(Optional.of(mockItem));

        GeneralItem result = inventoryItemService.findGeneralItem(itemId);

        assertEquals(mockItem, result);
    }

    @Test
    void testFindQuantizableItem() {
        String itemId = "123";
        QuantizableItem mockItem = new QuantizableItem();
        when(quantizableItemRepository.findByItemId(itemId)).thenReturn(Optional.of(mockItem));

        QuantizableItem result = inventoryItemService.findQuantizableItem(itemId);

        assertEquals(mockItem, result);
    }


    @Test
    void testCreateItem_CategoryNotExist() throws JsonProcessingException {
        // Mocking
        GeneralItemDTO generalItemDTO = new GeneralItemDTO();
        when(categoryRepository.existsById(any())).thenReturn(false);

        assertThrows(IllegalParameterInRequest.class, () -> inventoryItemService.createGeneralItem(generalItemDTO));
        verify(generalItemRepository, never()).save(any());
    }


    @Test
    void testCreateItem_ItemExist() throws JsonProcessingException {

        GeneralItemDTO generalItemDTO = new GeneralItemDTO();

        when(categoryRepository.findCategoryById(any())).thenReturn(new Category());
        when(categoryRepository.existsById(any())).thenReturn(true);
        when(generalItemRepository.existsById(any())).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> inventoryItemService.createGeneralItem(generalItemDTO));
        verify(generalItemRepository, never()).save(any());
    }


    @Test
    void createQuantizableItem_InitialQuantityZero() {
        // Mocking
        QuantizableItemDTO quantizableItemDTO = new QuantizableItemDTO();
        quantizableItemDTO.setQuantity(0);

        // Test & Verify
        assertThrows(IllegalParameterInRequest.class, () -> inventoryItemService.createQuantizableItem(quantizableItemDTO));
        verify(generalItemRepository, never()).save(any());
    }










}