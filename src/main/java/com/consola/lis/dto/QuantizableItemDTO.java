package com.consola.lis.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantizableItemDTO extends GeneralItemDTO{

    @NotBlank(message = "The quantity  is required")
    @Min(value = 0, message = "The quantity must be greater than or equal to zero")
    private int quantity;

}
