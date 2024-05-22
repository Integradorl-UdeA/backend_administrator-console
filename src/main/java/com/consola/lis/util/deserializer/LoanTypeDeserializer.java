package com.consola.lis.util.deserializer;

import com.consola.lis.model.enums.LoanType;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class LoanTypeDeserializer extends JsonDeserializer<LoanType> {

    @Override
    public LoanType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String loanTypeLowerCase = jsonParser.getText();
        return LoanType.fromString(loanTypeLowerCase);
    }
}
