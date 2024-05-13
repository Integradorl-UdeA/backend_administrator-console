package com.consola.lis.service;


import com.consola.lis.dto.ItemInfoDTO;
import com.consola.lis.util.constans.Util;
import com.consola.lis.dto.InventoryItemDTO;
import com.consola.lis.util.exception.AlreadyExistsException;
import com.consola.lis.util.exception.IllegalParameterInRequest;
import com.consola.lis.util.exception.NotExistingException;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.model.enums.ItemState;
import com.consola.lis.model.enums.WalletOwners;
import com.consola.lis.model.repository.CategoryRepository;
import com.consola.lis.model.repository.InventoryItemRepository;
import com.consola.lis.util.mapper.InventoryItemMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.*;
import java.util.stream.Collectors;


@Service

public class InventoryItemService {

    private final ObjectMapper objectMapper;
    private final InventoryItemRepository inventoryItemRepository;
    private final CategoryRepository categoryRepository;


    private final Map<Boolean, Map<Boolean, List<String>>> validStatesMap = new HashMap<>();

    public InventoryItemService(ObjectMapper objectMapper,
                                InventoryItemRepository inventoryItemRepository,
                                CategoryRepository categoryRepository) {
        this.objectMapper = objectMapper;
        this.inventoryItemRepository = inventoryItemRepository;
        this.categoryRepository = categoryRepository;

        validStatesMap.put(true, new HashMap<>());
        validStatesMap.put(false, new HashMap<>());

        validStatesMap.get(true).put(true, Arrays.asList(Util.VALID_STATE_CUANT_LEND.split(",")));
        validStatesMap.get(true).put(false, Arrays.asList(Util.VALID_STATES_CUANT_NOTLEND.split(",")));
        validStatesMap.get(false).put(true, Arrays.asList(Util.VALID_STATES_NOTCUAN_LEND.split(",")));
        validStatesMap.get(false).put(false, Arrays.asList(Util.VALID_STATES_NOT_CUAN_LEND.split(",")));
    }

    public InventoryItem createInventoryItem(InventoryItemDTO inventoryItemRequest) throws JsonProcessingException {

        if (inventoryItemRequest.getQuantity() == 0) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The provided initial quantity must be greater than zero");
        }

        validateCategoryExists(inventoryItemRequest.getCategoryId());

        Category category = categoryRepository.findCategoryById(inventoryItemRequest.getCategoryId());
        boolean isQuantizable = category.getQuantizable() != null ? category.getQuantizable() : false;
        boolean isLendable = inventoryItemRequest.getLendable() != null ? inventoryItemRequest.getLendable() : false;


        if (existItem(inventoryItemRequest.getItemId())) {
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "Item already exists into inventary");
        } else {
            validateState(inventoryItemRequest.getState().name(), isQuantizable, isLendable);

            String attributesJson = objectMapper.writeValueAsString(inventoryItemRequest.getAttributes());
            if (inventoryItemRequest.getWallet() == null || inventoryItemRequest.getWallet().name().trim().isEmpty()) {
                inventoryItemRequest.setWallet(WalletOwners.NOT_APPLY);
            }

            InventoryItem inventoryItem = createNewItem(inventoryItemRequest, attributesJson);

            return inventoryItemRepository.save(inventoryItem);
        }
    }

    private InventoryItem createNewItem(InventoryItemDTO inventoryItemRequest, String attributesJson) {
        return InventoryItem.builder()
                .itemId(inventoryItemRequest.getItemId())
                .categoryId(inventoryItemRequest.getCategoryId())
                .wallet(WalletOwners.valueOf(inventoryItemRequest.getWallet().name()))
                .lendable(inventoryItemRequest.getLendable())
                .state(ItemState.valueOf(inventoryItemRequest.getState().name()))
                .attributes(attributesJson)
                .quantity(inventoryItemRequest.getQuantity())
                .total(inventoryItemRequest.getQuantity())
                .build();
    }


    public void validateCategoryExists(Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The provided category id is not valid");
        }
    }

    public void validateState(String state, boolean isQuantizable, boolean isLendable) {
        List<String> validStates = validStatesMap.getOrDefault(isQuantizable, new HashMap<>())
                .getOrDefault(isLendable, List.of());

        if (!validStates.contains(state)) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The provided state is not valid for the item type");
        }
    }

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }


    @Transactional
    public void deleteInventoryItem(String itemId) {
        if (existItem(itemId)) {
            inventoryItemRepository.deleteById(itemId);
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the item whit id " + itemId + "not exist");
        }
    }


    public InventoryItem findInventoryItem(String itemId) {
        Optional<InventoryItem> item = inventoryItemRepository.findById(itemId);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the category whit id " + itemId + " not exist");
        }
    }


    public boolean existItem(String itemId) {
        return inventoryItemRepository.existsById(itemId);
    }


    public Map<String, Object> getAllItemsMapped(Pageable pageable) {
        Page<InventoryItem> inventoryItemsPage = getAllInventoryItems(pageable);

        Map<String, Object> result = new HashMap<>();
        result.put("totalElements", inventoryItemsPage.getTotalElements());
        result.put("totalPages", inventoryItemsPage.getTotalPages());
        result.put("currentPage", inventoryItemsPage.getNumber());
        result.put("items", mapToItemInfoList(inventoryItemsPage.getContent()));

        return result;
    }

    private List<ItemInfoDTO> mapToItemInfoList(List<InventoryItem> items) {
        return items.stream()
                .map(item -> InventoryItemMapper.mapToItemInfo(item, findCategory(item)))
                .toList();
    }

    private Page<InventoryItem> getAllInventoryItems(Pageable pageable) {
        return inventoryItemRepository.findAllItems(pageable);
    }
    public List<String> getHeaders() {
        List<String> header = new ArrayList<>();
        header.add("Id");
        header.add("Estado");
        header.add("Categoría");
        header.add("Cartera");
        header.add("Atributos");
        header.add("Acciones");
        return header;
    }

    public Category findCategory(InventoryItem generalItem) {
        return categoryRepository.findCategoryById(generalItem.getCategoryId());
    }

    public InventoryItem findItemById(String itemId) {
        return inventoryItemRepository.findById(itemId)
                .orElseThrow(() -> new NotExistingException("409", HttpStatus.CONFLICT, "Item not exists into inventary"));
    }

    public void updateInventoryItemState(String itemId, ItemState state) {
        InventoryItem existingItem = findItemById(itemId);

        existingItem.setState(state);
        inventoryItemRepository.save(existingItem);
    }

    public void updateInventoryItemTotal(String itemId, int quantityChange) {
        InventoryItem existingItem = findItemById(itemId);

        existingItem.setTotal(existingItem.getTotal() + quantityChange);
        inventoryItemRepository.save(existingItem);
    }

    public void changeStateNoQuantizableItem(InventoryItem item, ItemState state) {
        if (Boolean.FALSE.equals(item.getCategory().getQuantizable())) {
            updateInventoryItemState(item.getItemId(), state);
        }
    }


    public InventoryItem updateInventoryItemQuantity(String itemId, int quantity) {
        InventoryItem existingItem = findItemById(itemId);

        if (existingItem.getQuantity() + quantity < 0) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The quantity " + itemId + " you want to restore is greater than current");
        }
        existingItem.setQuantity(existingItem.getQuantity() + quantity);
        existingItem.setTotal(existingItem.getTotal() + quantity);
        return inventoryItemRepository.save(existingItem);
    }




}