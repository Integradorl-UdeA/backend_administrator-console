package com.consola.lis.util.mapper;

import com.consola.lis.dto.ItemInfoDTO;
import com.consola.lis.model.entity.Category;
import com.consola.lis.model.entity.InventoryItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InventoryItemMapper {
    private InventoryItemMapper () {
    }
    public static ItemInfoDTO mapToItemInfo(InventoryItem item, Category category) {

        return ItemInfoDTO.builder()
                .id(item.getItemId())
                .state(String.valueOf(item.getState()))
                .category(category.getCategoryName())
                .wallet(String.valueOf(item.getWallet()).replace("_"," "))
                .quantizable(category.getQuantizable())
                .attributes(convertAttributes(item.getAttributes()))
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
