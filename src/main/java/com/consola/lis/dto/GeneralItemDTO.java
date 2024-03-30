package com.consola.lis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralItemDTO {

    @NotBlank(message = "The  ID item  is required")
    private String itemId;

    @NotBlank(message = "The item name c is required")
    private String itemName;

    @NotBlank(message = "The category  is required")
    private int categoryId;

    @NotBlank(message = "The lendable  is required")
    private Boolean lendable;

    @NotBlank(message = "The state  is required")
    private String state;

    private Map<String, String> attributes;

}
