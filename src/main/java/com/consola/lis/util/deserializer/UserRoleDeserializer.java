package com.consola.lis.util.deserializer;

import com.consola.lis.model.enums.UserRole;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class UserRoleDeserializer extends JsonDeserializer<UserRole> {

    @Override
    public UserRole deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String roleNumericString = jsonParser.getText();
        return UserRole.fromString(roleNumericString);
    }
}
