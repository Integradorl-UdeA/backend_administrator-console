package com.consola.lis.model.enums;

import com.consola.lis.util.deserializer.LoanTypeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = LoanTypeDeserializer.class)
public enum LoanType {
    GENERAL("General"),
    BOOK ("Libro"),
    IOT("IOT"),
    PI("Proyecto Integrador"),
    OTHER("Otro");

    final String lowerCase;

    LoanType(String lowerCase){
        this.lowerCase = lowerCase;
    }
    @Override
    public String toString() {
        return this.lowerCase;
    }

    public static LoanType fromString(String text) {
        for (LoanType loan : LoanType.values()) {
            if (loan.lowerCase.equalsIgnoreCase(text)) {
                return loan;
            }
        }
        throw new IllegalArgumentException("No enum constant with text " + text);
    }

    public static List<String> getLowerCaseValuesList() {
        List<String> lowerCaseValues = new ArrayList<>();
        for (LoanType loan : LoanType.values()) {
            lowerCaseValues.add(loan.toString());
        }
        return lowerCaseValues;
    }

}
