package com.consola.lis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotBlank(message = "Category name is required")
    private String categoryName;

    @NotNull(message = "Quantizable status is required")
    private Boolean quantizable;

    @NotNull(message = "Attributes array is required")
    private String[] attributes;

    @NotNull(message = "List of attributes is required")
    private ListAttributeDTO[] listAttributes;
}
