package com.consola.lis.dto;

import com.consola.lis.model.enums.ItemState;
import com.consola.lis.model.enums.WalletOwners;
import com.consola.lis.util.deserializer.ItemStateDeserializer;
import com.consola.lis.util.deserializer.WalletOwnersDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryItemDTO {

    @NotBlank(message = "The  ID item  is required")
    private String itemId;

    @NotBlank(message = "The category  is required")
    private int categoryId;

    @JsonDeserialize(using = WalletOwnersDeserializer.class)
    private WalletOwners wallet;

    @NotBlank(message = "The lendable  is required")
    private Boolean lendable;

    @JsonDeserialize(using = ItemStateDeserializer.class)
    @NotBlank(message = "The state  is required")
    private ItemState state;

    @NotBlank(message = "The quantity  is required")
    @Min(value = 1)
    private int quantity;

    private ListAttributeItemDTO[] attributes;

}
