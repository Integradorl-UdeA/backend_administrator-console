package com.consola.lis.model.enums;

import com.consola.lis.util.deserializer.ItemStateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = ItemStateDeserializer.class)
public enum ItemState {
    AVAILABLE("Disponible"),
    BROKEN("Averiado"),
    REPAIRING("En reparación"),
    LOST("Perdido"),
    PRESENT("Presente"),
    LENDED("Prestado"),
    OUT_OF_STOCK("Sin Stock");

    final String lowerCase;

    ItemState(String lowerCase){
        this.lowerCase = lowerCase;
    }
    @Override
    public String toString() {
        return this.lowerCase;
    }

    public static ItemState fromString(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }
        for (ItemState state : ItemState.values()) {
            if (state.lowerCase.equalsIgnoreCase(text)) {
                return state;
            }
        }
        throw new IllegalArgumentException("No enum constant with text " + text);
    }



    public static List<String> getLowerCaseValuesList() {
        List<String> lowerCaseValues = new ArrayList<>();
        for (ItemState state : ItemState.values()) {
            lowerCaseValues.add(state.toString());
        }
        return lowerCaseValues;
    }
}
