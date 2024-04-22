package com.consola.lis.controller;

import com.consola.lis.model.enums.StateItem;
import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.dto.InventoryItemDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.service.InventoryItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Tag(name = "Manage for Inventory", description = "something")
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_INVENTORY)
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryItemService inventoryItemService;


    @PostMapping(EndpointConstant.ENDPOINT_INVENTORY_ITEM )
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItemDTO inventoryItemDTO) throws JsonProcessingException {
        InventoryItem createdGeneralItem = inventoryItemService.createInventoryItem(inventoryItemDTO);
        return ResponseEntity.ok(createdGeneralItem);
    }

    @GetMapping
    public List<InventoryItem> items(){
        return inventoryItemService.getAllInventoryItems();
    }

    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_ITEM)
    public void deleteItem(@PathVariable("itemId") String itemId){
        inventoryItemService.deleteInventoryItem(itemId);
    }

    @GetMapping(EndpointConstant.ENDPOINT_INVENTORY_TABLE)
    public Map<String, Object> inventoryItems() {
        return inventoryItemService.getAllItemsMapped();
    }

    @GetMapping(EndpointConstant.ENDPOINT_ONE_ITEM)
    public InventoryItem findInventoryItem(@PathVariable("itemId") String itemId){
        return inventoryItemService.findInventoryItem(itemId);
    }

    @PatchMapping(EndpointConstant.ENDPOINT_EDIT_ITEM_STATE)
    public void updateInventoryItem(@PathVariable("itemId")String itemId, @RequestBody StateItem state) {
        inventoryItemService.updateInventoryItemState(itemId,state);
    }


}
