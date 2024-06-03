package com.consola.lis.controller;

import com.consola.lis.dto.TableRegistersItemDTO;
import com.consola.lis.dto.TableRegistersLoanDTO;
import com.consola.lis.model.enums.ItemState;
import com.consola.lis.util.constans.ApiDescription;
import com.consola.lis.util.constans.EndpointConstant;
import com.consola.lis.dto.InventoryItemDTO;
import com.consola.lis.model.entity.InventoryItem;
import com.consola.lis.service.InventoryItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

@Tag(name = "Manage for Inventory", description = ApiDescription.DESCRIPTION_CONTROLLER_INVENTORY)
@RestController
@RequestMapping(EndpointConstant.ENDPOINT_INVENTORY)
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryItemService inventoryItemService;

    @Operation(summary = ApiDescription.DESCRIPTION_CREATE_ITEM_INVENTORY)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItemDTO inventoryItemDTO) throws JsonProcessingException {
        InventoryItem createdGeneralItem = inventoryItemService.createInventoryItem(inventoryItemDTO);
        return ResponseEntity.ok(createdGeneralItem);
    }

    @Operation(summary = ApiDescription.DESCRIPTION_INVENTORY)
    @GetMapping
    public List<InventoryItem> items(){
        return inventoryItemService.getAllInventoryItems();
    }

    @Operation(summary = ApiDescription.DESCRIPTION_DELETE_ITEM)
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(EndpointConstant.ENDPOINT_DELETE_ITEM)
    public void deleteItem(@PathVariable("itemId") String itemId){
        inventoryItemService.deleteInventoryItem(itemId);
    }


    @Operation(summary = ApiDescription.DESCRIPTION_INVENTORY_TABLE)
    @PostMapping(EndpointConstant.ENDPOINT_INVENTORY_TABLE)
    public Map<String, Object> inventoryItems(@RequestBody TableRegistersItemDTO tableRegistersDTO){
        Pageable pageable = PageRequest.of(tableRegistersDTO.getPage(), tableRegistersDTO.getSize());
        return inventoryItemService.getAllItemsMapped(tableRegistersDTO.getLendable(),pageable);
    }

    @Operation(summary = ApiDescription.DESCRIPTION_ONE_ITEM)
    @GetMapping(EndpointConstant.ENDPOINT_ONE_ITEM)
    public ResponseEntity<InventoryItem> findInventoryItem(@PathVariable("itemId") String itemId){
        return ResponseEntity.ok(inventoryItemService.findInventoryItem(itemId));
    }

    @Operation(summary = ApiDescription.DESCRIPTION_EDIT_ITEM_STATE)
    @PatchMapping(EndpointConstant.ENDPOINT_EDIT_ITEM_STATE)
    public void updateInventoryItem(@PathVariable("itemId")String itemId, @RequestBody ItemState state) {
        inventoryItemService.updateInventoryItemState(itemId,state);
    }

    @Operation(summary = ApiDescription.DESCRIPTION_EDIT_QUANTITY)
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(EndpointConstant.ENDPOINT_EDIT_QUANTITY)
    public ResponseEntity<InventoryItem> updateInventoryItemQuantity(@PathVariable("itemId")String itemId, @RequestBody int quantity) {
        return ResponseEntity.ok(inventoryItemService.updateInventoryItemQuantity(itemId, quantity ));

    }

    @Operation(summary = ApiDescription.DESCRIPTION_HEADERS_LOAN)
    @GetMapping(EndpointConstant.ENDPOINT_HEADERS_ITEM)
    public List<String> getHeaders(){
        return inventoryItemService.getHeaders();
    }



}
