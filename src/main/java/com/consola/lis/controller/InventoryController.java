package com.consola.lis.controller;

import com.consola.lis.constans.EndpointConstant;
import com.consola.lis.dto.GeneralItemDTO;
import com.consola.lis.dto.QuantizableItemDTO;
import com.consola.lis.model.entity.GeneralItem;
import com.consola.lis.model.entity.QuantizableItem;
import com.consola.lis.service.InventoryItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(EndpointConstant.ENDPOINT_INVENTORY)
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryItemService inventoryItemService;


    @PostMapping(EndpointConstant.ENDPOINT_INVENTORY_GENERAL_ITEM )
    public ResponseEntity<GeneralItem> createGeneralItem(@RequestBody GeneralItemDTO generalItemDTO) throws JsonProcessingException {
        GeneralItem createdGeneralItem = inventoryItemService.createGeneralItem(generalItemDTO);
        return ResponseEntity.ok(createdGeneralItem);
    }

    @PostMapping(EndpointConstant.ENDPOINT_INVENTORY_QUANTIZABLE_ITEM)
    public ResponseEntity<QuantizableItem> createQuantizableItem(@RequestBody QuantizableItemDTO quantizableItemDTO) throws JsonProcessingException {
        QuantizableItem createdQuantizableItem = inventoryItemService.createQuantizableItem(quantizableItemDTO);
        return ResponseEntity.ok(createdQuantizableItem);
    }

    @GetMapping(EndpointConstant.ENDPOINT_INVENTORY_ALL_GENERAL_ITEM)
    public List<GeneralItem> generalItems(){
        return inventoryItemService.getAllGeneralItems();
    }

    @GetMapping(EndpointConstant.ENDPOINT_INVENTORY_ALL_QUANTIZABLE_ITEM)
    public List<QuantizableItem> quantizableItems(){
        return inventoryItemService.getAllQuantizableItems();
    }

    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_ITEM)
    public void deleteCategory(@PathVariable("itemId") String itemId){
        inventoryItemService.deleteItem(itemId);
    }

    @GetMapping
    public List<Object> inventoryItems() {
        return inventoryItemService.getAllItems();
    }

    @GetMapping(EndpointConstant.ENDPOINT_ONE_GENERAL_ITEM)
    public GeneralItem findGeneralItem(@PathVariable("itemId") String itemId){
        return inventoryItemService.findGeneralItem(itemId);
    }

    @GetMapping(EndpointConstant.ENDPOINT_ONE_QUANTIZABLE_ITEM)
    public QuantizableItem findQuantizableItem(@PathVariable("itemId") String itemId){
        return inventoryItemService.findQuantizableItem(itemId);
    }




}
