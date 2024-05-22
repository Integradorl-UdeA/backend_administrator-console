package com.consola.lis.util.deserializer;

import com.consola.lis.model.enums.ItemState;
import com.consola.lis.model.enums.LoanState;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ItemStateDeserializer extends JsonDeserializer<ItemState> {

    @Override
    public ItemState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String itemStateLowerCase = jsonParser.getText();
        return ItemState.fromString(itemStateLowerCase);
    }
}
