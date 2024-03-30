package com.consola.lis.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoryDTO {
    @NotBlank(message = "Category name is required")
    private String categoryName;

    @NotBlank(message = "Quantizable status is required")
    private Boolean quantizable;

    @NotBlank(message = "Attributes array is required")
    private String[] attributes;

    @NotBlank(message = "List of attributes is required")
    private ListAttributeDTO[] listAttributes;
}
