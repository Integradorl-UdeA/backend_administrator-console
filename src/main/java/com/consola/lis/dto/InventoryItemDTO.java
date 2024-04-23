package com.consola.lis.dto;

import com.consola.lis.model.enums.StateItem;
import com.consola.lis.model.enums.WalletOwners;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private WalletOwners wallet;

    @NotBlank(message = "The lendable  is required")
    private Boolean lendable;

    @NotBlank(message = "The state  is required")
    private StateItem state;

    @NotBlank(message = "The quantity  is required")
    @Min(value = 1)
    private int quantity;

    private ListAttributeItemDTO[] attributes;

}
