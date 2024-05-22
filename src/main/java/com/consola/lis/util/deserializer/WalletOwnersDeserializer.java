package com.consola.lis.util.deserializer;

import com.consola.lis.model.enums.WalletOwners;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class WalletOwnersDeserializer extends JsonDeserializer<WalletOwners> {
    @Override
    public WalletOwners deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String walletOwnerLoweCase = jsonParser.getText();
        return WalletOwners.fromString(walletOwnerLoweCase);
    }
}
