package com.consola.lis.model.enums;


import com.consola.lis.util.deserializer.UserRoleDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = UserRoleDeserializer.class)
public enum UserRole {
    ADMIN ("Administrador"),
    AUXPROG ("Auxiliar de Programación"),
    AUXADMI("Auxiliar Administrativo"),
    STUDENT("Estudiante"),
    GRADUATED("Egresado"),
    PROFESSOR("Profesor");

    final String lowerCase;

    UserRole(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }

    public static UserRole fromString(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }

        for (UserRole role : UserRole.values()) {
            if (role.lowerCase.equalsIgnoreCase(text)) {
                return role;
            }
        }

        return fromNumericString(text);
    }




    public static UserRole fromNumericString(String numericString) {
        switch (numericString) {
            case "1005":
                return STUDENT;
            case "503":
                return PROFESSOR;
            case "502":
                return AUXPROG;
            case "1006":
                return AUXADMI;
            default:
                return null; // O maneja el caso de un valor desconocido
        }
    }

    public static List<String> getLowerCaseValuesList() {
        List<String> lowerCaseValues = new ArrayList<>();
        for (UserRole role : UserRole.values()) {
            lowerCaseValues.add(role.toString());
        }
        return lowerCaseValues;
    }
}
