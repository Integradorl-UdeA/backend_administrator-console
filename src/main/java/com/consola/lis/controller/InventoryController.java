package com.consola.lis.controller;

import com.consola.lis.model.enums.ItemState;
import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.dto.InventoryItemDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.service.InventoryItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

@Tag(name = "Manage for Inventory", description = "something")
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_INVENTORY)
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryItemService inventoryItemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItemDTO inventoryItemDTO) throws JsonProcessingException {
        InventoryItem createdGeneralItem = inventoryItemService.createInventoryItem(inventoryItemDTO);
        return ResponseEntity.ok(createdGeneralItem);
    }

    @GetMapping
    public List<InventoryItem> items(){
        return inventoryItemService.getAllInventoryItems();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_ITEM)
    public void deleteItem(@PathVariable("itemId") String itemId){
        inventoryItemService.deleteInventoryItem(itemId);
    }


    @GetMapping(EndpointConstant.ENDPOINT_INVENTORY_TABLE)
    public Map<String, Object> inventoryItems(Pageable pageable) {
        return inventoryItemService.getAllItemsMapped(pageable);
    }

    @GetMapping(EndpointConstant.ENDPOINT_ONE_ITEM)
    public ResponseEntity<InventoryItem> findInventoryItem(@PathVariable("itemId") String itemId){
        return ResponseEntity.ok(inventoryItemService.findInventoryItem(itemId));
    }

    @PatchMapping(EndpointConstant.ENDPOINT_EDIT_ITEM_STATE)
    public void updateInventoryItem(@PathVariable("itemId")String itemId, @RequestBody ItemState state) {
        inventoryItemService.updateInventoryItemState(itemId,state);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(EndpointConstant.ENDPOINT_EDIT_QUANTITY)
    public ResponseEntity<InventoryItem> updateInventoryItemQuantity(@PathVariable("itemId")String itemId, @RequestBody int quantity) {
        return ResponseEntity.ok(inventoryItemService.updateInventoryItemQuantity(itemId, quantity ));

    }


    @GetMapping(EndpointConstant.ENDPOINT_HEADERS_ITEM)
    public List<String> getHeaders(){
        return inventoryItemService.getHeaders();
    }



}
