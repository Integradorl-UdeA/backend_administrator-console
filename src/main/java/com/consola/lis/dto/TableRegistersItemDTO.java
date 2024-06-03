package com.consola.lis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableRegistersItemDTO {
    private int page;
    private int size;
    private Boolean lendable;
}
