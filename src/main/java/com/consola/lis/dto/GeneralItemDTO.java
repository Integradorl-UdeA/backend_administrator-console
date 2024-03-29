package com.consola.lis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralItemDTO {

    private String itemId;
    private String itemName;
    private int categoryId;
    private boolean lendable;
    private String state;
    private String attributes;

}
