package com.consola.lis.model.enums;


import java.util.ArrayList;
import java.util.List;

public enum WalletOwners {
    DIEGO_ALEJANDRO_BOTIA ("Diego Alejandro Botia"),
    ANDRES_MARIN("Andres Marin"),
    LUIS_SILVA("Luis Silva"),
    NOT_APPLY("No Aplica");

    final String lowerCase;
    WalletOwners(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }

    public static WalletOwners fromString(String text) {
        for (WalletOwners owner : WalletOwners.values()) {
            if (owner.lowerCase.equalsIgnoreCase(text)) {
                return owner;
            }
        }
        throw new IllegalArgumentException("No enum constant with text " + text);
    }

    public static List<String> getLowerCaseValuesList() {
        List<String> lowerCaseValues = new ArrayList<>();
        for (WalletOwners owner : WalletOwners.values()) {
            lowerCaseValues.add(owner.toString());
        }
        return lowerCaseValues;
    }

}
