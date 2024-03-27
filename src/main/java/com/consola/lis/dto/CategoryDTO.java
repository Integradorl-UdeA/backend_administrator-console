package com.consola.lis.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoryDTO {
    private String categoryName;
    private Boolean quantizable;
    private String attributes;
    private String listAttributes;
}
