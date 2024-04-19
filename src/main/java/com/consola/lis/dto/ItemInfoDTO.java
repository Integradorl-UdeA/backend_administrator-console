package com.consola.lis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfoDTO {
    private String id;
    private String state;
    private String category;
    private String wallet;
    private String[] attributes;
    private boolean quantizable;
}
