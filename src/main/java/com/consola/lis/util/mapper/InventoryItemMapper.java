package com.consola.lis.util.mapper;

import com.consola.lis.dto.ItemInfoDTO;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.GeneralItem;
import com.consola.lis.model.entity.QuantizableItem;
import org.json.JSONObject;

import java.util.Set;

public class InventoryItemMapper {
    private InventoryItemMapper () {
    }
    public static ItemInfoDTO mapToItemInfoGene(GeneralItem generalItem, Category category) {

        return ItemInfoDTO.builder()
                .id(generalItem.getItemId())
                .state(String.valueOf(generalItem.getState()))
                .category(category.getCategoryName())
                .wallet(String.valueOf(generalItem.getWallet()).replace("_"," "))
                .quantizable(category.getQuantizable())
                .attributes(convertAttributes(generalItem.getAttributes()))
                .build();
    }

    public static ItemInfoDTO mapToItemInfoQuant(QuantizableItem quantizableItem, Category category) {

        GeneralItem generalItem = quantizableItem.getGeneralItem();
        return ItemInfoDTO.builder()
                .id(String.valueOf(quantizableItem.getQuantizableItemId()))
                .state(String.valueOf(generalItem.getState()))
                .category(category.getCategoryName())
                .wallet(String.valueOf(generalItem.getWallet()).replace("_"," "))
                .quantizable(category.getQuantizable())
                .attributes(convertAttributes(generalItem.getAttributes()))
                .build();
    }



    private static String[] convertAttributes(String attributes){
        JSONObject jsonObject = new JSONObject(attributes);
        Set<String> keySet = jsonObject.keySet();
        return keySet.toArray(new String[0]);
    }
}
