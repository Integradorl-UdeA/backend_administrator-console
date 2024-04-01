package com.consola.lis.service;


import com.consola.lis.constans.Util;
import com.consola.lis.dto.GeneralItemDTO;
import com.consola.lis.dto.QuantizableItemDTO;
import com.consola.lis.exception.AlreadyExistsException;
import com.consola.lis.exception.IllegalParameterInRequest;
import com.consola.lis.exception.NotExistingException;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.GeneralItem;
import com.consola.lis.model.entity.QuantizableItem;
import com.consola.lis.model.enums.StateItem;
import com.consola.lis.model.enums.WalletOwners;
import com.consola.lis.model.repository.CategoryRepository;
import com.consola.lis.model.repository.GeneralItemRepository;
import com.consola.lis.model.repository.QuantizableItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class InventoryItemService {

    private final ObjectMapper objectMapper;
    private final GeneralItemRepository generalItemRepository;
    private final QuantizableItemRepository quantizableItemRepository;
    private final CategoryRepository categoryRepository;

    private final Map<Boolean, Map<Boolean, List<String>>> validStatesMap = new HashMap<>();

    public InventoryItemService(ObjectMapper objectMapper,
                                GeneralItemRepository generalItemRepository,
                                QuantizableItemRepository quantizableItemRepository,
                                CategoryRepository categoryRepository) {
        this.objectMapper = objectMapper;
        this.generalItemRepository = generalItemRepository;
        this.quantizableItemRepository = quantizableItemRepository;
        this.categoryRepository = categoryRepository;

        validStatesMap.put(true, new HashMap<>());
        validStatesMap.put(false, new HashMap<>());

        validStatesMap.get(true).put(true, Arrays.asList(Util.VALID_STATE_CUANT_LEND.split(",")));
        validStatesMap.get(true).put(false, Arrays.asList(Util.VALID_STATES_CUANT_NOTLEND.split(",")));
        validStatesMap.get(false).put(true, Arrays.asList(Util.VALID_STATES_NOTCUAN_LEND.split(",")));
        validStatesMap.get(false).put(false, Arrays.asList(Util.VALID_STATES_NOT_CUAN_LEND.split(",")));


    }
    public GeneralItem createGeneralItem(GeneralItemDTO generalItemRequest) throws JsonProcessingException {

        validateCategoryExists(generalItemRequest.getCategoryId());

        Category category = categoryRepository.findCategoryById(generalItemRequest.getCategoryId());

        boolean isQuantizable = category.getQuantizable() != null ? category.getQuantizable() : false;
        boolean isLendable = generalItemRequest.getLendable() != null ? generalItemRequest.getLendable() : false;
        boolean existingGeneralItem = generalItemRepository.existsById(generalItemRequest.getItemId());

        System.out.println("existing item "+existingGeneralItem);

        if (existingGeneralItem) {
            throw new AlreadyExistsException("409", HttpStatus.CONFLICT, "Item already exists into inventary");
        } else {
            validateState(generalItemRequest.getState(), isQuantizable, isLendable);

            String attributesJson = objectMapper.writeValueAsString(generalItemRequest.getAttributes());
            if (generalItemRequest.getWallet() == null || generalItemRequest.getWallet().trim().isEmpty()) {
                generalItemRequest.setWallet(String.valueOf(WalletOwners.NOT_APPLY));

            }
            validateWalletOwner(generalItemRequest.getWallet());

            GeneralItem generalItem = GeneralItem.builder()
                    .itemId(generalItemRequest.getItemId())
                    .itemName(generalItemRequest.getItemName())
                    .categoryId(generalItemRequest.getCategoryId())
                    .wallet(WalletOwners.valueOf(generalItemRequest.getWallet()))
                    .lendable(generalItemRequest.getLendable())
                    .state(StateItem.valueOf(generalItemRequest.getState()))
                    .attributes(attributesJson)
                    .build();

            return generalItemRepository.save(generalItem);
        }
    }


    public QuantizableItem createQuantizableItem(QuantizableItemDTO quantizableItemRequest) throws JsonProcessingException {

        if (quantizableItemRequest.getQuantity() == 0) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The provided initial quantity must be greater than zero");
        }

        GeneralItemDTO generalItemDTO = GeneralItemDTO.builder()
                .itemId(quantizableItemRequest.getItemId())
                .itemName(quantizableItemRequest.getItemName())
                .categoryId(quantizableItemRequest.getCategoryId())
                .wallet(quantizableItemRequest.getWallet())
                .lendable(quantizableItemRequest.getLendable())
                .state(quantizableItemRequest.getState())
                .attributes(quantizableItemRequest.getAttributes())
                .build();

        createGeneralItem(generalItemDTO);

        QuantizableItem quantizableItem = QuantizableItem.builder()
                .itemId(quantizableItemRequest.getItemId())
                .quantity(quantizableItemRequest.getQuantity())
                .total(quantizableItemRequest.getQuantity())
                .build();

        return quantizableItemRepository.save(quantizableItem);
    }



    public void validateCategoryExists(Integer categoryId) {
        System.out.println("category"+categoryRepository.existsById(categoryId));
        System.out.println("category"+!categoryRepository.existsById(categoryId));
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The provided category id is not valid");
        }
    }

    public void validateWalletOwner(String wallerOwner){
        List<String> walletOwners = Arrays.asList(Util.VALID_WALLET_OWNERS.split(","));
        String normalizedOwner = wallerOwner.trim().toUpperCase();
        if (!walletOwners.contains(normalizedOwner)){
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The provided wallet owner name is not valid");
        }
    }

    public void validateState(String state, boolean isQuantizable, boolean isLendable) {
        List<String> validStates = validStatesMap.getOrDefault(isQuantizable, new HashMap<>())
                .getOrDefault(isLendable, List.of());

        if (!validStates.contains(state)) {
            throw new IllegalParameterInRequest("400", HttpStatus.BAD_REQUEST, "The provided state is not valid for the item type");
        }
    }

    public List<GeneralItem> getAllGeneralItems() {
        return generalItemRepository.findAll();
    }

    public List<QuantizableItem> getAllQuantizableItems() {
        return quantizableItemRepository.findAll();
    }


    @Transactional
    public void deleteItem(String itemId) {

        if (existItem(itemId)) {
            generalItemRepository.deleteById(itemId);
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the item whit id " + itemId + "not exist");
        }
    }

    public GeneralItem findGeneralItem(String itemId) {
        Optional<GeneralItem> generalItem = generalItemRepository.findById(itemId);
        if (generalItem.isPresent()) {
            return generalItem.get();
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the category whit id " + itemId + " not exist");
        }
    }

    public QuantizableItem findQuantizableItem(String itemId) {
        Optional<QuantizableItem> quantizableItem = quantizableItemRepository.findByItemId(itemId);
        if (quantizableItem.isPresent()) {
            return quantizableItem.get();
        } else {
            throw new NotExistingException("404", HttpStatus.NOT_FOUND, " the category whit id " + itemId + " not exist");
        }
    }

    public boolean existItem(String itemId) {
        return generalItemRepository.existsById(itemId);
    }

    public List<Object> getAllItems() {

        List<GeneralItem> generalItems = getAllGeneralItems();
        List<Object> inventoryItems = new ArrayList<>(generalItems);
        List<QuantizableItem> quantizableItems = getAllQuantizableItems();
        inventoryItems.addAll(quantizableItems);

        return  inventoryItems;

    }



}
