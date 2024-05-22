package com.consola.lis.util.deserializer;

import com.consola.lis.model.enums.LoanState;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class LoanStateDeserializer extends JsonDeserializer<LoanState> {

    @Override
    public LoanState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String loanStateLowerCase = jsonParser.getText();
        return LoanState.fromString(loanStateLowerCase);
    }
}
