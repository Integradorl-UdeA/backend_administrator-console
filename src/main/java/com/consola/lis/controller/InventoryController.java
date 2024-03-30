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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
