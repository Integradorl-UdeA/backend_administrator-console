package com.consola.lis.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum LoanState {
    ACTIVE ("Activo"),
    RETURNED("Devuelto"),
    DELAYED("Retrasado");

    final String lowerCase;

    LoanState(String lowerCase){
        this.lowerCase = lowerCase;
    }
    @Override
    public String toString() {
        return this.lowerCase;
    }

    public static LoanState fromString(String text) {
        for (LoanState state : LoanState.values()) {
            if (state.lowerCase.equalsIgnoreCase(text)) {
                return state;
            }
        }
        throw new IllegalArgumentException("No enum constant with text " + text);
    }

    public static List<String> getLowerCaseValuesList() {
        List<String> lowerCaseValues = new ArrayList<>();
        for (LoanState state : LoanState.values()) {
            lowerCaseValues.add(state.toString());
        }
        return lowerCaseValues;
    }
}
