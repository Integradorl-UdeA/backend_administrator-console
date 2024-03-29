package com.consola.lis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuantizableItemDTO extends GeneralItemDTO{

    private int quantity;
    private int total;

}
