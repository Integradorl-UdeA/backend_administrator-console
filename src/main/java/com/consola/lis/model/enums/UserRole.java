package com.consola.lis.model.enums;

import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

public enum UserRole {
    ADMIN ("Administrador"),
    AUXPROG ("Auxiliar de Programación"),
    AUXADMI("Auxiliar de Administrativo"),
    STUDENT("Estudiante"),
    PROFESSOR("Profesor");

    final String lowerCase;

    UserRole(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    @Override
    public String toString() {
        return this.lowerCase;
    }

    public static UserRole fromString(String role) {
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return fromNumericString(role);
        }
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
