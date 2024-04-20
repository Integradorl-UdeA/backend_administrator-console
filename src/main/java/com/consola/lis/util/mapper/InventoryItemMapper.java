package com.consola.lis.util.mapper;

import com.consola.lis.dto.ItemInfoDTO;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.GeneralItem;
import com.consola.lis.model.entity.QuantizableItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InventoryItemMapper {
    private InventoryItemMapper () {
    }
    public static ItemInfoDTO mapToItemInfo(GeneralItem generalItem, Category category) {

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



    private static String[] convertAttributes(String attributes) throws JSONException {
        JSONArray jsonArray = new JSONArray(attributes);
        List<String> values = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            values.add(obj.getString("name"));
        }

        return values.toArray(new String[0]);
    }

}
