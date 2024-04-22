package com.consola.lis.service;

import com.consola.lis.dto.InventoryItemDTO;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.IllegalParameterInRequest;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.model.repository.CategoryRepository;
import com.consola.lis.model.repository.InventoryItemRepository;
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
    private InventoryItemRepository inventoryItemRepository;

    //@Mock
   // private QuantizableItemRepository quantizableItemRepository;

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
        List<InventoryItem> mockedList = Arrays.asList(new InventoryItem(), new InventoryItem());
        when(inventoryItemRepository.findAll()).thenReturn(mockedList);

        List<InventoryItem> result = inventoryItemService.getAllInventoryItems();

        assertEquals(mockedList, result);
    }

//    @Test
//    void testGetAllQuantizableItems() {
//        List<QuantizableItem> mockedList = Arrays.asList(new QuantizableItem(), new QuantizableItem());
//        when(quantizableItemRepository.findAll()).thenReturn(mockedList);
//
//        List<QuantizableItem> result = inventoryItemService.getAllQuantizableItems();
//
//        assertEquals(mockedList, result);
//    }

    @Test
    void testDeleteItem() {
        String itemId = "123";
        when(inventoryItemRepository.existsById(itemId)).thenReturn(true);

        assertDoesNotThrow(() -> inventoryItemService.deleteInventoryItem(itemId));

        verify(inventoryItemRepository, times(1)).deleteById(itemId);
    }

    @Test
    void testFindGeneralItem() {
        String itemId = "123";
        InventoryItem mockItem = new InventoryItem();
        when(inventoryItemRepository.findById(itemId)).thenReturn(Optional.of(mockItem));

        InventoryItem result = inventoryItemService.findInventoryItem(itemId);

        assertEquals(mockItem, result);
    }

//    @Test
//    void testFindQuantizableItem() {
//        String itemId = "123";
//        QuantizableItem mockItem = new QuantizableItem();
//        when(quantizableItemRepository.findByItemId(itemId)).thenReturn(Optional.of(mockItem));
//
//        QuantizableItem result = inventoryItemService.findQuantizableItem(itemId);
//
//        assertEquals(mockItem, result);
//    }


    @Test
    void testCreateItem_CategoryNotExist() throws JsonProcessingException {
        // Mocking
        InventoryItemDTO inventoryItemDTO = new InventoryItemDTO();
        when(categoryRepository.existsById(any())).thenReturn(false);

        assertThrows(IllegalParameterInRequest.class, () -> inventoryItemService.createInventoryItem(inventoryItemDTO));
        verify(inventoryItemRepository, never()).save(any());
    }


    @Test
    void testCreateItem_ItemExist() throws JsonProcessingException {

        InventoryItemDTO inventoryItemDTO = new InventoryItemDTO();

        when(categoryRepository.findCategoryById(any())).thenReturn(new Category());
        when(categoryRepository.existsById(any())).thenReturn(true);
        when(inventoryItemRepository.existsById(any())).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> inventoryItemService.createInventoryItem(inventoryItemDTO));
        verify(inventoryItemRepository, never()).save(any());
    }


//    @Test
//    void createQuantizableItem_InitialQuantityZero() {
//        // Mocking
//        QuantizableItemDTO quantizableItemDTO = new QuantizableItemDTO();
//        quantizableItemDTO.setQuantity(0);
//
//        // Test & Verify
//        assertThrows(IllegalParameterInRequest.class, () -> inventoryItemService.createInventoryItem(quantizableItemDTO));
//        verify(inventoryItemRepository, never()).save(any());
//    }










}